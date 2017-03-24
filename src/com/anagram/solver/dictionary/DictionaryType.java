package com.anagram.solver.dictionary;

import java.util.Arrays;
import java.util.List;

public enum DictionaryType {
	TWL("twl"),
	SOWPODS("sowpods");
	
	private static DictionaryType defaultType;
	
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
				.findAny().orElse(null);
	}
	
	public static void setDefaultType(DictionaryType newDefaultDict) {
		defaultType = newDefaultDict;
	}
	
	public static DictionaryType defaultType() {
		return defaultType == null ? TWL : defaultType;
	}
}
