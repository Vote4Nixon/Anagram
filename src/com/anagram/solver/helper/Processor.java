package com.anagram.solver.helper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.anagram.solver.dictionary.DictionaryType;
import com.anagram.solver.dictionary.WordResult;
import com.anagram.solver.option.Option;
import com.anagram.solver.option.OptionType;

public class Processor {
	public static void process(String input) throws IOException {
		List<Option> options = Parser.parse(input);
		List<String> errors = Parser.errors(options);
		
		if(errors.size() > 0) { 
			print(errors);
		}
		else {
			execute(options);
		}
	}
	
	public static void print(List<String> errors) {
		System.out.println();
		errors.forEach(e -> System.out.println(e));
		System.out.println();
	}
	
	public static void execute(List<Option> options) throws IOException {
		if(hasOption(options, OptionType.EXIT)) {
			System.exit(0);
		}
		else if(hasOption(options, OptionType.HELP)) {
			System.out.println("\nUsage: <input> [option1] [option2] [option3] ...");
			OptionType.valuesAsList().stream()
				.forEach(ot -> System.out.printf("%n%-20.20s  %-20s%n", ot.header(), ot.details()));
			System.out.println();
		}
		else {
			String dict = optionValue(options, OptionType.DICTIONARY);
			
			if(dict != null) {
				DictionaryType.setDefaultDict(DictionaryType.find(dict));
			}
			
			String input = optionValue(options, OptionType.INPUT);
			String start = optionValue(options, OptionType.START);
			String end = optionValue(options, OptionType.END);
			String contain = optionValue(options, OptionType.CONTAIN);
			String pattern = optionValue(options, OptionType.PATTERN);
			
			WordResult results = Solver.solve(input, start, end, contain, pattern);
			
			System.out.println();
			results.entrySet().stream().forEach(e -> {
				System.out.println(e.getKey() + "-Letter Words");
				System.out.println(e.getValue().stream().collect(Collectors.joining(", ")));
				System.out.println();
			});
		}
	}
	
	private static boolean hasOption(List<Option> options, OptionType type) {
		return options.stream()
				.anyMatch(o -> o.type() == type);
	}
	
	private static String optionValue(List<Option> options, OptionType type) {
		Option option = options.stream()
				.filter(o -> o.type() == type)
				.findFirst().orElse(null);
		
		return option == null ? null : option.value();
	}
}
