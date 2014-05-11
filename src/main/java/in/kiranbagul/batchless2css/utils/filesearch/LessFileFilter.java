package in.kiranbagul.batchless2css.utils.filesearch;

import java.io.File;

public class LessFileFilter implements Filter {
	private String includePattern;
	private String excludePattern;
	private String[] excludeArray;

	public LessFileFilter(String includePattern, String excludePattern) {
		this.includePattern = includePattern;
		this.excludePattern = excludePattern;
		this.excludeArray = excludePattern.split(",");
	}

	public boolean filter(File paramFile) {
		String fileName = paramFile.getAbsolutePath();
		boolean toExclude = excludePattern.isEmpty() ? false : matchesExclude(fileName);
		return fileName.endsWith(includePattern) && !toExclude;
	}

	private boolean matchesExclude(String fileName) {
		for(String excludeEntry : excludeArray){
			if(fileName.endsWith(excludeEntry)){
				return true;
			}
		}
		return false;
	}

}
