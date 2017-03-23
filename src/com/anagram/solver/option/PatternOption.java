package com.anagram.solver.option;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternOption extends Option {

	public PatternOption(String value) {
		super(OptionType.PATTERN, value);
	}
	
	@Override
	public String error() {
		if(value == null || value.equals("")) {
			return "No pattern given";
		}
		
		try {
			Pattern.compile(value);
		}
		catch(PatternSyntaxException e) {
			return "Invalid pattern given";
		}
		
		return null;
	}

}
