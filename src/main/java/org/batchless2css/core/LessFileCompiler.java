package org.batchless2css.core;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.batchless2css.utils.logging.LoggerFactory;
import org.lesscss.LessSource;

public class LessFileCompiler implements Runnable{
	private static final Logger LOGGER = LoggerFactory.getLogger(LessFileCompiler.class.getName());

	private File src;
	private File output;
	
	public LessFileCompiler(File inputSrc, File output) {
		this.src = inputSrc;
		this.output = output;
	}
	
	public void run() {
		compile();
	}
	
	private void compile() {
		String absolutePath = this.src.getAbsolutePath();
		try {
			if (CompilerEnv.isForceOverwrite() || isSourceChanged(output)) {
				CompilerEnv.getInstance().compile(this.src, output);
				LOGGER.info("Compiled "+absolutePath);  
			}
		} catch (Exception e) {
			CompilerEnv.processError("Error in compiling file " + absolutePath + "\n" + e);
		}
	}

	private boolean isSourceChanged(File output) throws IOException {
		return !this.output.exists() || this.output.lastModified() < new LessSource(this.src).getLastModifiedIncludingImports();
	}
}
