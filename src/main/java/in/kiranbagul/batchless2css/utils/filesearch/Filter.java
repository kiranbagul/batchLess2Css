package in.kiranbagul.batchless2css.utils.filesearch;

import java.io.File;

public abstract interface Filter {
	public abstract boolean filter(File paramFile);
}