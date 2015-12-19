package example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.filter.ExtentionFilter;
import example.filter.Filter;
import example.filter.NameFilter;
import example.filter.SizeFilter;

public class Demo {

	public static void main(String[] args) {

		Filter filter = new NameFilter(null, "super");
		filter = new ExtentionFilter(filter, "txt");
		filter = new SizeFilter(filter, 10);

		File file = new File("D:\\filter");

		List<File> files = Arrays.asList(file.listFiles());
		
		System.out.println(files);
		List<File> result = new ArrayList<>();
		for (File current : files) {
			if (filter.accept(current)) {
				result.add(current);
			}
		}
		System.out.println(result);
	}
}