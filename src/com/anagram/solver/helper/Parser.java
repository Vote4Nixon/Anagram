package com.anagram.solver.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.anagram.solver.option.DictionaryOption;
import com.anagram.solver.option.ExitOption;
import com.anagram.solver.option.HelpOption;
import com.anagram.solver.option.InputOption;
import com.anagram.solver.option.Option;
import com.anagram.solver.option.OptionType;
import com.anagram.solver.option.PatternOption;

public class Parser {
	public static List<Option> parse(String input) {
		List<Option> options = new ArrayList<>();
		
		if(input == null) {
			return options;
		}
		
		String[] parsedInput = input.split("\\s+");
		
		for(String optionInput : parsedInput) {
			OptionType type = null;
			String value = null;
			Option option = null;
			
			String[] parsedOptionInput = optionInput.split(":");
			
			if(parsedOptionInput.length >= 1) {
				if(parsedOptionInput[0].startsWith("-")) {
					type = OptionType.find(parsedOptionInput[0]);
				}
				else {
					type = OptionType.INPUT;
					value = parsedOptionInput[0];
				}
				
				if(value == null && parsedOptionInput.length >= 2) {
					value = parsedOptionInput[1];
				}
			}
			
			switch(type) {
			case DICTIONARY:
				option = new DictionaryOption(value);
				break;
			case EXIT:
				option = new ExitOption(value);
				break;
			case HELP:
				option = new HelpOption(value);
				break;
			case INPUT:
				option = new InputOption(value);
				break;
			case PATTERN:
				option = new PatternOption(value);
				break;
			default:
				break;
			}
			
			if(option != null) {
				options.add(option);
			}
		}
		
		return options;
	}
	
	public static List<String> errors(List<Option> options) {
		return options.stream()
				.map(o -> o.error())
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}
}
