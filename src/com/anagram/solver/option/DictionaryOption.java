package com.anagram.solver.option;

public class DictionaryOption extends Option {
	
	public DictionaryOption(String value) {
		super(OptionType.DICTIONARY, value);
	}

	@Override
	public String error() {
		if(value == null || value.equals("")) {
			return "No dictionary given";
		}
		if(!value.equals("twl") && !value.equals("sowpods")) {
			return "Invalid dictionary given";
		}
		
		return null;
	}

}
