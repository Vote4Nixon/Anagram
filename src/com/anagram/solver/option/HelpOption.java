package com.anagram.solver.option;

public class HelpOption extends Option {

	public HelpOption(String value) {
		super(OptionType.HELP, value);
	}
	
	@Override
	public String error() {
		return null;
	}

}
