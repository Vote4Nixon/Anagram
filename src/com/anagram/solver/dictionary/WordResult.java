package com.anagram.solver.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class WordResult implements IWordStore {
	private Map<Long, Set<String>> results;
	
	public WordResult() {
		results = new HashMap<>();
	}

	@Override
	public void add(String word) {
		long length = convert(word);
		Set<String> words = null;
		
		if(results.containsKey(length)) {
			words = results.get(length);
		}
		else {
			words = new TreeSet<>();
			results.put(length, words);
		}
		
		words.add(word);
	}

	@Override
	public Set<String> get(String word) {
		Set<String> words = results.get(convert(word));
		
		return words == null ? new TreeSet<>() : words;
	}

	@Override
	public long convert(String word) {
		return word.length();
	}
	
	public Set<Entry<Long, Set<String>>> entrySet() {
		return results.entrySet();
	}
}
