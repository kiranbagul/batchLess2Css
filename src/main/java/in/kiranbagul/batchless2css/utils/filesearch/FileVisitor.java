package in.kiranbagul.batchless2css.utils.filesearch;

import java.io.File;

public abstract interface FileVisitor {
	public abstract void visitFile(File paramFile);
}