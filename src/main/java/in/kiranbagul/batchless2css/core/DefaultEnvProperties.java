package in.kiranbagul.batchless2css.core;


public interface DefaultEnvProperties {
	
	String INPUT_FILES_PATH = ".";
	
	String INCLUDES = ".less";
	
	String EXCLUDES = ".module.less";
	
	boolean FORCE_OVERWRITE = false;
	
	int NO_OF_THREADS = 6;
	
	String OUTPUT_AS = ".gen.css";
	
	boolean COMPRESS = false;
	
	boolean FAIL_ON_ERROR = true;

}
