package com.anagram.solver.option;

public class StartOption extends Option {

	public StartOption(String value) {
		super(OptionType.START, value);
	}
	
	@Override
	public String error() {
		if(value == null || value.equals("")) {
			return "No start letters given";
		}
		if(!value.matches("[a-z]+")) {
			return "Start letters can only contain the letters a-z";
		}
		
		return null;
	}

}
