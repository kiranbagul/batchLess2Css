package in.kiranbagul.utils.filesearch;

import java.io.File;

public abstract interface Filter {
	public abstract boolean filter(File paramFile);
}