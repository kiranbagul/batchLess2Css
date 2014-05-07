package org.batchless2css.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import org.batchless2css.utils.logging.LoggerFactory;
import org.lesscss.LessSource;

public class LessFileCompiler implements Callable<Boolean>{
	private static final Logger LOGGER = LoggerFactory.getLogger(LessFileCompiler.class.getName());

	private File src;
	private File output;
	
	public LessFileCompiler(File inputSrc, File output) {
		this.src = inputSrc;
		this.output = output;
	}
	
	private boolean compile() {
		String absolutePath = this.src.getAbsolutePath();
		boolean status = false;
		try {
			if (CompilerEnv.isForceOverwrite() || isSourceChanged(output)) {
				CompilerEnv.getInstance().compile(this.src, output);
				LOGGER.info("Compiled "+absolutePath);  
				status = true;
			}
		} catch (Exception e) {
			CompilerEnv.processError("Error in compiling file " + absolutePath + "\n", e);
		}
		return status;
	}

	private boolean isSourceChanged(File output) throws IOException {
		return !this.output.exists() || this.output.lastModified() < new LessSource(this.src).getLastModifiedIncludingImports();
	}

	public Boolean call() throws Exception {
		return compile();
	}
}
