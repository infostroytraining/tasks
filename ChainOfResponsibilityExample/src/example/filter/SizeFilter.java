package example.filter;

import java.io.File;

public class SizeFilter extends Filter {

	private long size;

	public SizeFilter(Filter next, long size) {
		super(next);
		this.size = size;
	}

	@Override
	public boolean currentAccept(File file) {
		if (file != null) {
			long fileSize = file.length();
			return fileSize < size;
		}
		return false;
	}
}
