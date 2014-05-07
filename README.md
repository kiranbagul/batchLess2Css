batchLess2Css
=============

Multithreaded Batch LESS to CSS Compiler

batchLess2Css compiler can be used when large number of LESS files needs to be compiled to css.

Building from source
--------------------
Create local maven reposotory for dependencies used

Run following command from base directory

mvn install:install-file -DlocalRepositoryPath=lib/repo \
  -DcreateChecksum=true -Dpackaging=jar \
  -Dfile=lib/lesscss-1.5.1.jar -DgroupId=org.lesscss \
  -DartifactId=org.lesscss -Dversion=1.5.1
  
mvn install:install-file -DlocalRepositoryPath=lib/repo \
  -DcreateChecksum=true -Dpackaging=jar \
  -Dfile=lib/args4j-2.0.26.jar -DgroupId=args4j \
  -DartifactId=args4j -Dversion=2.0.26


Generate executable jar file : 
mvn clean install 

Command Line Usage 
------------------

java -jar batchless2css-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

 -compress     : compress css files, default : false
 -exclude      : file match pattern to exclude in directory scan, default : .module.less
 -failOnError  : terminate if compilation error occurs, default : true
 -force        : if false, compile only when file input file is changed
                 (including the imports), default : false
 -include      : file match pattern to include in directory scan, default : .less
 -outputas     : string will appended to output file, default : .gen.css
 -path         : file match pattern to include in directory scan, default : current directory
 -threads      : no of threads used for processing, default : 6
 
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
args4j
lesscss-java

