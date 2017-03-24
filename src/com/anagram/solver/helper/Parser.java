package com.anagram.solver.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.anagram.solver.option.ContainOption;
import com.anagram.solver.option.DictionaryOption;
import com.anagram.solver.option.EndOption;
import com.anagram.solver.option.ExitOption;
import com.anagram.solver.option.HelpOption;
import com.anagram.solver.option.InputOption;
import com.anagram.solver.option.Option;
import com.anagram.solver.option.OptionType;
import com.anagram.solver.option.PatternOption;
import com.anagram.solver.option.StartOption;

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
			String inputFlag = null;
			String inputValue = null;
			
			if(optionInput != null && optionInput.contains(":")) {
				inputFlag = optionInput.substring(0, optionInput.indexOf(":"));
				inputValue = optionInput.substring(optionInput.indexOf(":") + 1);
			}
			else if(optionInput != null) {
				inputFlag = optionInput;
				inputValue = null;
			}
			
			if(inputFlag != null) {
				if(inputFlag.startsWith("-")) {
					type = OptionType.find(inputFlag);
				}
				else {
					type = OptionType.INPUT;
					value = inputFlag;
				}
				
				if(value == null && inputValue != null) {
					value = inputValue;
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
			case START:
				option = new StartOption(value);
				break;
			case END:
				option = new EndOption(value);
				break;
			case CONTAIN:
				option = new ContainOption(value);
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
