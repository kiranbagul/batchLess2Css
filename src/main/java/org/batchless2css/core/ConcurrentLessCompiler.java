package org.batchless2css.core;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.batchless2css.utils.filesearch.DirectoryScanner;
import org.batchless2css.utils.filesearch.FileVisitor;
import org.batchless2css.utils.logging.LoggerFactory;

public class ConcurrentLessCompiler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentLessCompiler.class.getName());
	Set<Future<Boolean>> set = new HashSet<Future<Boolean>>();
	
	private long startTime;
	
	protected void start() {
		LOGGER.info("Compiling LESS files from "+CompilerEnv.getInputFilesPath());
		startTimer();
		final ExecutorService executor = Executors.newFixedThreadPool(CompilerEnv.getNoOfThreads());
		DirectoryScanner directoryScanner = new DirectoryScanner(new File(CompilerEnv.getInputFilesPath()), CompilerEnv.getFileFilter());
		directoryScanner.scan(new FileVisitor() {
			public void visitFile(final File file) {
				processFile(executor, file);
			}
		});
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			int fileCount = 0;
			for (Future<Boolean> future : set) {
				fileCount = future.get() ? (fileCount + 1) : fileCount; 
			}
			logTime(fileCount);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			LOGGER.severe("Compile could not be completed. Terminating");  
		}
	}

	private void processFile(ExecutorService executor, File file) {
		File outputFile = new File(file.getAbsolutePath().replace(".less", CompilerEnv.getOutputas()));
		Callable<Boolean> compilerThread = new LessFileCompiler(file, outputFile);
		Future<Boolean> future = executor.submit(compilerThread);
		set.add(future);
	}

	private void logTime(int count) {
		long diffInMS = (System.currentTimeMillis() - startTime);
		LOGGER.info(String.format(
				"Compiled %d LESS files to CSS in %d min, %d sec", count,
				TimeUnit.MILLISECONDS.toMinutes(diffInMS),
				TimeUnit.MILLISECONDS.toSeconds(diffInMS) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diffInMS))));
	}

	private void startTimer() {
		startTime = System.currentTimeMillis();
	}

}
