package com.anagram.solver.option;

public class ContainOption extends Option {

	public ContainOption(String value) {
		super(OptionType.CONTAIN, value);
	}
	
	@Override
	public String error() {
		if(value == null || value.equals("")) {
			return "No contain letters given";
		}
		if(!value.matches("[a-z]+")) {
			return "Contain letters can only contain the letters a-z";
		}
		
		return null;
	}

}
