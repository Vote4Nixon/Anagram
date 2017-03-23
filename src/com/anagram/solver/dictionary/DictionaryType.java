package com.anagram.solver.dictionary;

import java.util.Arrays;
import java.util.List;

public enum DictionaryType {
	TWL("twl"),
	SOWPODS("sowpods");
	
	private static DictionaryType defaultDict;
	
	private String title;
	
	private DictionaryType(String title) {
		this.title = title;
	}
	
	public String title() {
		return title;
	}
	
	public static List<DictionaryType> valuesAsList() {
		return Arrays.asList(values());
	}
	
	public static DictionaryType find(String title) {
		return valuesAsList().stream()
				.filter(t -> t.title.equals(title))
				.findAny().get();
	}
	
	public static void setDefaultDict(DictionaryType newDefaultDict) {
		defaultDict = newDefaultDict;
	}
	
	public static DictionaryType defaultDict() {
		return defaultDict == null ? TWL : defaultDict;
	}
}
