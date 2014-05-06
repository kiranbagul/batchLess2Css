package org.batchless2css.utils.filesearch;

import java.io.File;

public class LessFileFilter implements Filter {
	private String includePattern;
	private String excludePattern;

	public LessFileFilter(String includePattern, String excludePattern) {
		this.includePattern = includePattern;
		this.excludePattern = excludePattern;
	}

	public boolean filter(File paramFile) {
		String fileName = paramFile.getAbsolutePath();
		boolean excludesParam = excludePattern.isEmpty() ? true : !fileName.endsWith(excludePattern);
		return fileName.endsWith(includePattern) && excludesParam;
	}

}
