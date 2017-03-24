package com.anagram.solver.option;

public class EndOption extends Option {

	public EndOption(String value) {
		super(OptionType.END, value);
	}
	
	@Override
	public String error() {
		if(value == null || value.equals("")) {
			return "No end letters given";
		}
		if(!value.matches("[a-z]+")) {
			return "End letters can only contain the letters a-z";
		}
		
		return null;
	}

}
