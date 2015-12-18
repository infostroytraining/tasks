package example;

import example.filter.ExtentionFilter;
import example.filter.Filter;
import example.filter.NameFilter;
import example.filter.SizeFilter;

public class Demo {
	
	public static void main(String[] args) {
	
		Filter filter = new NameFilter(null, "super");
		filter = new SizeFilter(filter, 10);
		filter = new ExtentionFilter(filter, ".txt");
		
			
	}
}
