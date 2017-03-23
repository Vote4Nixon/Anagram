package com.anagram.solver.dictionary;

import java.util.Set;

public interface IWordStore {
	public void add(String word);
	public Set<String> get(String word);
	public long convert(String word);
}
