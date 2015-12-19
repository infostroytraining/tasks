package example.filter;

import java.io.File;

import com.google.common.io.Files;

public class NameFilter extends Filter {

	private String name;

	public NameFilter(Filter next, String name) {
		super(next);
		this.name = name;
	}

	@Override
	public boolean currentAccept(File file) {
		if (file != null){
			String fileName = Files.getNameWithoutExtension(file.getName());
			return fileName.startsWith(name);
		}
		return false;
	}
}
