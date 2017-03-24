package com.anagram.solver.option;

import java.util.Arrays;
import java.util.List;

public enum OptionType {
	INPUT(null, "<input>", "A series of letters to search anagrams for.  You may use 2-10 letters, including up to 2 wildcards denoted by ?.", "abc? -- find all words containing a, b, c, and any letter a-z"),
	START("-s", "<letters>", "Show only words that start with the given letters.", "arthet -s:th -- find words from the input 'arthet' that start with 'th'"),
	END("-e", "<letters>", "Show only words that end with the given letters.", "dream -e:er -- find words from the input 'dream' that end with 'er'"),
	CONTAIN("-c", "<letters>", "Show only words that contain the given letters.", "abcdef -c:bad -- find words from the input 'abcdef' that contain 'bad'"),
	PATTERN("-p", "<regex>", "Filter words by matching them against a Java Regular Expression (see https://docs.oracle.com/javase/tutorial/essential/regex/index.html).", "ademiooah? -p:.*[aeiou]{3,}.* -- find words from the input 'ademiooah?' that contain at least 3 vowels"),
	DICTIONARY("-d", "[twl|sowpods]", "Sets the default dictionary, either TWL (American) or SOWPODS (International).  The starting dictionary is TWL.", "-d:sowpods"),
	HELP("-h", null, "Display the help menu.", null),
	EXIT("-e", null, "Exit the program.", null);
	
	private String flag;
	private String input;
	private String description;
	private String usage;
	
	private OptionType(String flag, String input, String description, String usage) {
		this.flag = flag;
		this.input = input;
		this.description = description;
		this.usage = usage;
	}
	
	public static List<OptionType> valuesAsList() {
		return Arrays.asList(values());
	}
	
	public static OptionType find(String flag) {
		return valuesAsList().stream()
				.filter(ot -> ot.flag == null ? (flag == null || flag == "") : ot.flag.equals(flag))
				.findAny().get();
	}
	
	public String header() {
		return (flag == null ? "" : flag) 
				+ (flag != null && input != null ? ":" : "") 
				+ (input == null ? "" : input);
	}
	
	public String details() {
		return description 
				+ (usage == null ? "" : " (e.g. " + usage + ")");
	}
}
