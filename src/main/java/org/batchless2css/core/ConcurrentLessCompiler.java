package org.batchless2css.core;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.batchless2css.utils.filesearch.DirectoryScanner;
import org.batchless2css.utils.filesearch.FileVisitor;
import org.batchless2css.utils.logging.LoggerFactory;


public class ConcurrentLessCompiler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentLessCompiler.class.getName());
	
	//Processed file counter
	private static int count = 0;

	private long startTime;
	
	protected void start() {
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
		} catch (InterruptedException e) {
			LOGGER.severe("Compile could not be completed. Terminating");  
		}
		logTime();
	}

	private void processFile(ExecutorService executor, File file) {
		File outputFile = new File(file.getAbsolutePath().replace(".less", CompilerEnv.getOutputas()));
		Runnable compilerThread = new LessFileCompiler(file, outputFile);
		executor.execute(compilerThread);
		count++;
	}

	private void logTime() {
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
