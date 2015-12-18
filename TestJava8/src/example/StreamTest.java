package example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {

		List<String> list = Arrays.asList("java", "ruby", "groovy", "c#", "python", "scala", "go", "c++", "basic");

		Map<String, Integer> result = list.stream().filter(item -> item.length() < 5)
												   .map(item -> new StringBuilder(item).reverse().toString())
												   .map(item -> item.toUpperCase())
												   .collect(Collectors.toMap(item -> item, item -> item.length()));
		System.out.println(result);

		List<String> resultList = result.keySet()
										.stream()
										.map(item -> new StringBuilder(item).reverse().toString())
										.map(item -> item.toLowerCase())
										.collect(Collectors.toList());
		
		System.out.println(resultList);
	}
}
