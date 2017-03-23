package com.anagram.solver.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.anagram.solver.dictionary.DictionaryType;
import com.anagram.solver.dictionary.WordDictionary;

public class Builder {
	private static Map<DictionaryType, WordDictionary> cache = new HashMap<>();
	
	public static WordDictionary build(DictionaryType type) throws IOException {
		if(cache.containsKey(type)) {
			return cache.get(type);
		}
		
		WordDictionary dict = new WordDictionary();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("com/anagram/solver/resources/" + type.title() + ".txt")));

	    while(reader.ready()) {
	    	String line = reader.readLine();
	    	
	    	if(line != null) {
	    		dict.add(line.trim());
	    	}
	    }
		
	    reader.close();
	    
	    cache.put(type, dict);
	    
	    return dict;
	}
	
	public static Map<DictionaryType, WordDictionary> buildAll() throws IOException {
		Map<DictionaryType, WordDictionary> map = new HashMap<>();
		
		for(DictionaryType t : DictionaryType.values()) {
			map.put(t, build(t));
		}
		
		return map;
	}
}
