batchLess2Css
=============

Multithreaded Batch LESS to CSS Compiler

batchLess2Css compiler can be used when large number of LESS files needs to be compiled to css.

Building from source
--------------------
Initialize local maven repository 

	mvn initialize

Generate executable jar with dependencies file

	mvn clean install 

Command Line Usage 
------------------

	java -jar batchless2css-0.0.1-SNAPSHOT-jar-with-dependencies.jar -force -path=path/to/folder -compress

	 -compress     : compress css files, default : false
	 -exclude      : file match pattern to exclude in directory scan, default : .module.less
	 -failOnError  : terminate if compilation error occurs, default : true
	 -force        : if false, compile only when file input file is changed
	                 (including the imports), default : false
	 -include      : file match pattern to include in directory scan, default : .less
	 -outputas     : string will appended to output file, default : .gen.css
	 -path         : file match pattern to include in directory scan, default : current directory
	 -threads      : no of threads used for processing, default : 6
	 -mapFolder    : Ex. /less/#/css/ if string '/less/' is found in filepath, replace the it with '/css/', 
	 		 Generated css files with put in new mapped folder. 
	 		 Allows comma seperated multiple values Ex. /less/#/css/,/lessmodules/#/cssmodules/
 
Java Usage
----------
    //Set the required options, otherwise set to default
		CompilerEnv.setInputFilesPath(".");
		CompilerEnv.setForceOverwrite(true);
		new ConcurrentLessCompiler().start();
 
Compatibility 
-------
Compatible with less-1.5.1.js 
https://github.com/less/less.js

Support
-------
Found an issue or feature request
https://github.com/kiranbagul/batchLess2Css/issues

Contributor
-----------
Abhishek Ash (ash.abhishek@gmail.com)

Libraries Used
--------------
args4j, 
lesscss-java

License
-------
MIT License (MIT)

