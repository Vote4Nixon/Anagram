package com.anagram.solver.option;

public class InputOption extends Option {
	
	public InputOption(String value) {
		super(OptionType.INPUT, value);
	}

	@Override
	public String error() {
		if(value == null || value.equals("")) {
			return "No input given";
		}
		if(!value.matches("[[a-z]*[?]*]+")) {
			return "Input can only contain letters and the wildcard character '?'";
		}
		if(value.length() < 2 || value.length() > 10) {
			return "Input must be between 2-10 letters";
		}
		if(value.length() - value.replace("?", "").length() > 2) {
			return "Input can only contain at most 2 wildcard characters";
		}
		
		return null;
	}

}
