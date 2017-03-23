package com.anagram.solver.option;

public class ExitOption extends Option {

	public ExitOption(String value) {
		super(OptionType.EXIT, value);
	}
	
	@Override
	public String error() {
		return null;
	}

}
