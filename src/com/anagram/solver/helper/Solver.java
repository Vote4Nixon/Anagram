package com.anagram.solver.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.anagram.solver.dictionary.DictionaryType;
import com.anagram.solver.dictionary.WordDictionary;
import com.anagram.solver.dictionary.WordResult;

public class Solver {
	private static Map<String, Boolean> hasBeenDone;
	private static String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	
	public static WordResult solve(String input, String start, String end, String contain, String pattern) throws IOException {
		hasBeenDone = new HashMap<>();
		WordResult result = new WordResult();
		
		if(input == null || input.length() == 0) {
			return result;
		}
		
		Set<String> inputs = new HashSet<>();
		String wildFreeInput = input.replace("?", "");
		int wildCount = input.length() - wildFreeInput.length();
		
		if(wildCount == 0) {
			inputs.add(input);
		}
		else if(wildCount == 1) {
			for(String a : letters) {
				inputs.add(wildFreeInput + a);
			}
		}
		else if(wildCount == 2) {
			for(String a : letters) {
				for(String b : letters) {
					inputs.add(wildFreeInput + a + b);
				}
			}
		}
		
		Set<String> anagrams = new HashSet<>();
		
		for(String i : inputs) {
			anagrams.addAll(solveHelper(i));
		}
		
		anagrams.stream().forEach(w -> {
			boolean valid = true;

			if(start != null && start.length() > 0) {
				valid &= w.startsWith(start);
			}
			if(end != null && end.length() > 0) {
				valid &= w.endsWith(end);
			}
			if(contain != null && contain.length() > 0) {
				valid &= w.contains(contain);
			}
			if(pattern != null && pattern.length() > 0) {
				valid &= w.matches(pattern);
			}
			
			if(valid) {
				result.add(w);
			}
		});
		
		return result;
	}
	
	private static Set<String> solveHelper(String input) throws IOException {
		if(hasBeenDone.containsKey(input) || input == null || input.length() < 2) {
			return new HashSet<>();
		}

		WordDictionary dict = Builder.build(DictionaryType.defaultDict());
		Set<String> anagrams = dict.get(input);
		
		for(int i = 0; i < input.length(); i++) {
			String subInput = input.substring(0, i) + input.substring(i + 1);
			
			anagrams.addAll(solveHelper(subInput));
		}
		
		hasBeenDone.put(input, true);
		
		return anagrams;
	}
}
