package com.anagram.solver.option;

public abstract class Option {
	protected OptionType type;
	protected String value;
	
	public Option(OptionType type, String value) {
		this.type = type;
		this.value = value == null ? null : value.toLowerCase();
	}
	
	public OptionType type() {
		return type;
	}
	
	public String value() {
		return value;
	}
	
	public abstract String error();
}
