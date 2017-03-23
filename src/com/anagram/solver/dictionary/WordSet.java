package com.anagram.solver.dictionary;

import java.util.HashSet;
import java.util.Set;

public class WordSet implements IWordStore {
	public Set<String> words;
	
	public WordSet() {
		words = new HashSet<>();
	}

	@Override
	public void add(String word) {
		words.add(word);
	}

	@Override
	public Set<String> get(String word) {
		return words;
	}

	@Override
	public long convert(String word) {
		return 0;
	}
}
