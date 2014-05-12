package in.kiranbagul.batchless2css.core;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Compiler {
	
	@Option(name="-path", usage="file match pattern to include in directory scan, default : current directory")
	private static String inputFilesPath = DefaultEnvProperties.INPUT_FILES_PATH;
	
	@Option(name="-include", usage="file match pattern to include in directory scan, default : .less")
	private static String includes = DefaultEnvProperties.INCLUDES;
	
	@Option(name="-exclude", usage="comma seperated file match pattern to exclude in directory scan")
	private static String excludes = DefaultEnvProperties.EXCLUDES;
	
	@Option(name="-force", usage="if false, compile only when file input file is changed (including the imports), default : false")
	private static boolean forceOverwrite = DefaultEnvProperties.FORCE_OVERWRITE;
	
	@Option(name="-threads", usage="no of threads used for preocessing, default : 6")
	private static int noOfThreads = DefaultEnvProperties.NO_OF_THREADS;
	
	@Option(name="-outputas", usage="string will appended to output file, default : .gen.css")
	private static String outputas = DefaultEnvProperties.OUTPUT_AS;
	
	@Option(name="-compress", usage="compress css files, default : false")
	private static boolean compress = DefaultEnvProperties.COMPRESS;
	
	@Option(name="-failOnError", usage="terminate if compilation error occurs, default : true")
	private static boolean failOnError = DefaultEnvProperties.FAIL_ON_ERROR;
	
	@Option(name="-mapFolder", usage="Ex. /less/#/css/; if string 'less' is found in filepath, replace the it with 'css', " +
			"and generated css files with put in new mapped folder.\n" +
			"Allows comma seperated multiple values.")
	private static String folderMap = "";
	
	public static void main(String[] args) {
		if(parseCmdLineArgs(args)){
			process();
		}
	}

	private static boolean parseCmdLineArgs(String[] args) {
		CmdLineParser parser = new CmdLineParser(new Compiler());
	    try {
	        parser.parseArgument(args);
	        return true;
	    } catch( CmdLineException e ) {
	        System.err.println(e.getMessage());
	        System.err.println("Error in parsing arguments.");
	        parser.printUsage(System.err);
	        return false;
	    }
	}

	private static void process() {
		CompilerEnv.setInputFilesPath(inputFilesPath);
		CompilerEnv.setIncludes(includes);
		CompilerEnv.setExcludes(excludes);
		CompilerEnv.setNoOfThreads(noOfThreads);
		CompilerEnv.setOutputas(outputas);
		CompilerEnv.setCompress(compress);
		CompilerEnv.setFailOnError(failOnError);
		CompilerEnv.setForceOverwrite(forceOverwrite);
		CompilerEnv.setFolderMap(folderMap);
		new ConcurrentLessCompiler().start();
	}

}
