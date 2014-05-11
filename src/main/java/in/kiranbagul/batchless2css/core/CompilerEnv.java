package in.kiranbagul.batchless2css.core;

import in.kiranbagul.batchless2css.utils.filesearch.Filter;
import in.kiranbagul.batchless2css.utils.filesearch.LessFileFilter;
import in.kiranbagul.batchless2css.utils.logging.LoggerFactory;

import java.io.File;
import java.util.logging.Logger;

import org.lesscss.LessCompiler;

public class CompilerEnv {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompilerEnv.class.getName());

	private static boolean failOnError = DefaultEnvProperties.FAIL_ON_ERROR;
	private static boolean isForceOverwrite = DefaultEnvProperties.FORCE_OVERWRITE;
	private static boolean compress = DefaultEnvProperties.COMPRESS;
	private static String inputFilesPath = DefaultEnvProperties.INPUT_FILES_PATH;
	private static String includes = DefaultEnvProperties.INCLUDES;
	private static String excludes = DefaultEnvProperties.EXCLUDES;
	private static String outputas = DefaultEnvProperties.OUTPUT_AS;
	private static int noOfThreads = DefaultEnvProperties.NO_OF_THREADS;

	private CompilerEnv() {
	}

	private static class SingletonHolder {
		private static final LessCompiler INSTANCE = new LessCompiler();
	}

	public static LessCompiler getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public void setFileCompress(boolean compress) {
		SingletonHolder.INSTANCE.setCompress(compress);
	}

	public static void processError(String msg, Exception e) {
		LOGGER.severe(msg);
		if(null!=e){
			e.printStackTrace();			
		}
		if (isFailOnError()) {
			System.exit(1);
		}
	}

	public static Filter getFileFilter() {
		return new LessFileFilter(getIncludes(), getExcludes());
	}

	public static boolean isFailOnError() {
		return failOnError;
	}

	public static void setFailOnError(boolean failOnError) {
		CompilerEnv.failOnError = failOnError;
	}

	public static boolean isForceOverwrite() {
		return isForceOverwrite;
	}

	public static void setForceOverwrite(boolean isForceOverwrite) {
		CompilerEnv.isForceOverwrite = isForceOverwrite;
	}

	public static boolean isCompress() {
		return compress;
	}

	public static void setCompress(boolean compress) {
		CompilerEnv.compress = compress;
	}

	public static String getInputFilesPath() {
		return inputFilesPath;
	}

	public static void setInputFilesPath(String inputFilesPath) {
		if(new File(inputFilesPath).exists()){
			CompilerEnv.inputFilesPath = inputFilesPath;
		}else{
			processError("Input path does not exists.", null);
		}
	}

	public static String getIncludes() {
		return includes;
	}

	public static void setIncludes(String includes) {
		CompilerEnv.includes = includes;
	}

	public static String getExcludes() {
		return excludes;
	}

	public static void setExcludes(String excludes) {
		CompilerEnv.excludes = excludes;
	}

	public static String getOutputas() {
		return outputas;
	}

	public static void setOutputas(String outputas) {
		CompilerEnv.outputas = outputas;
	}

	public static int getNoOfThreads() {
		return noOfThreads;
	}

	public static void setNoOfThreads(int noOfThreads) {
		CompilerEnv.noOfThreads = noOfThreads;
	}

}
