package com.anagram.solver.dictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordDictionary implements IWordStore {
	public Map<Long, WordMap> wordMaps;
	
	public WordDictionary() {
		wordMaps = new HashMap<>();
	}

	@Override
	public void add(String word) {
		long length = convert(word);
		WordMap map = null;
		
		if(wordMaps.containsKey(length)) {
			map = wordMaps.get(length);
		}
		else {
			map = new WordMap();
			wordMaps.put(length, map);
		}
		
		map.add(word);
	}

	@Override
	public Set<String> get(String word) {
		WordMap map = wordMaps.get(convert(word));
		
		return map == null ? new HashSet<>() : map.get(word);
	}

	@Override
	public long convert(String word) {
		return word.length();
	}
}
