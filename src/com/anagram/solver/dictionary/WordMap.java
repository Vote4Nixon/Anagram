package com.anagram.solver.dictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordMap implements IWordStore {
	public Map<Long, WordSet> wordSets;
	private Map<Character, Integer> letterToPrime;
	
	public WordMap() {
		wordSets = new HashMap<>();
		
		letterToPrime = new HashMap<>();
		letterToPrime.put('a', 2);
		letterToPrime.put('b', 3);
		letterToPrime.put('c', 5);
		letterToPrime.put('d', 7);
		letterToPrime.put('e', 11);
		letterToPrime.put('f', 13);
		letterToPrime.put('g', 17);
		letterToPrime.put('h', 19);
		letterToPrime.put('i', 23);
		letterToPrime.put('j', 29);
		letterToPrime.put('k', 31);
		letterToPrime.put('l', 37);
		letterToPrime.put('m', 41);
		letterToPrime.put('n', 43);
		letterToPrime.put('o', 47);
		letterToPrime.put('p', 53);
		letterToPrime.put('q', 59);
		letterToPrime.put('r', 61);
		letterToPrime.put('s', 67);
		letterToPrime.put('t', 71);
		letterToPrime.put('u', 73);
		letterToPrime.put('v', 79);
		letterToPrime.put('w', 83);
		letterToPrime.put('x', 89);
		letterToPrime.put('y', 97);
		letterToPrime.put('z', 101);
	}

	@Override
	public void add(String word) {
		long id = convert(word);
		WordSet set = null;
		
		if(wordSets.containsKey(id)) {
			set = wordSets.get(id);
		}
		else {
			set = new WordSet();
			wordSets.put(id, set);
		}
		
		set.add(word);
	}

	@Override
	public Set<String> get(String word) {
		WordSet set = wordSets.get(convert(word));
		
		return set == null ? new HashSet<>() : set.get(word);
	}

	@Override
	public long convert(String word) {
		long result = 1;
		
		for(char c : word.toCharArray()) {
			result *= letterToPrime.get(c);
		}
		
		return result;
	}
}
