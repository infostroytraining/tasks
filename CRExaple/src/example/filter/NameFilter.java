package example.filter;

import java.io.File;

public class NameFilter extends Filter {

	private String name;

	public NameFilter(Filter next, String name) {
		super(next);
		this.name = name;
	}

	@Override
	public boolean currentAccept(File file) {
		if (file != null){
			String fileName = file.getName();
			return name.startsWith(fileName);
		}
		return false;
	}
}
