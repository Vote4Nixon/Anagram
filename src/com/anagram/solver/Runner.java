package com.anagram.solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.anagram.solver.dictionary.DictionaryType;
import com.anagram.solver.dictionary.WordResult;
import com.anagram.solver.helper.Parser;
import com.anagram.solver.helper.Solver;
import com.anagram.solver.option.Option;
import com.anagram.solver.option.OptionType;

public class Runner {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		ClassLoader.getSystemResourceAsStream("com/anagram/solver/resources/twl.txt");
		
		System.out.print("Anagram Solver!  Type a series of letters or -h for help\n\nInput: ");
		
		while(true) {
			String input = reader.readLine();
			
			List<Option> options = Parser.parse(input);
			List<String> errors = Parser.errors(options);
			
			if(errors.size() > 0) { 
				print(errors);
			}
			else {
				execute(options);
			}
			
			System.out.print("Input: ");
		}
	}
	
	private static void print(List<String> errors) {
		System.out.println();
		errors.forEach(e -> System.out.println(e));
		System.out.println();
	}
	
	private static void execute(List<Option> options) throws IOException {
		if(options.stream().anyMatch(o -> o.type() == OptionType.EXIT)) {
			System.exit(0);
		}
		else if(options.stream().anyMatch(o -> o.type() == OptionType.HELP)) {
			OptionType.valuesAsList().stream()
				.forEach(ot -> System.out.printf("%n%-20.20s  %-20s%n", ot.header(), ot.details()));
			System.out.println();
		}
		else {
			Option inputOpt = options.stream().filter(o -> o.type() == OptionType.INPUT).findFirst().orElse(null);
			Option patternOpt = options.stream().filter(o -> o.type() == OptionType.PATTERN).findFirst().orElse(null);
			Option dictOpt = options.stream().filter(o -> o.type() == OptionType.DICTIONARY).findFirst().orElse(null);
			
			if(dictOpt != null) {
				DictionaryType.setDefaultDict(DictionaryType.find(dictOpt.value()));
			}
			
			String input = inputOpt == null ? null : inputOpt.value();
			String pattern = patternOpt == null ? null : patternOpt.value();
			
			WordResult results = Solver.solve(input, pattern);
			
			System.out.println();
			results.entrySet().stream().forEach(e -> {
				System.out.println(e.getKey() + "-Letter Words");
				System.out.println(e.getValue().stream().collect(Collectors.joining(", ")));
				System.out.println();
			});
		}
	}

}
