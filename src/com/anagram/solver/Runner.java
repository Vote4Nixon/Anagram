package com.anagram.solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.anagram.solver.helper.Processor;

public class Runner {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String optionInput = "-p:([a-z]*[aeiou][a-z]*){4,}";
		String inputFlag;
		String inputValue;
		
		if(optionInput.contains(":")) {
			inputFlag = optionInput.substring(0, optionInput.indexOf(":"));
			inputValue = optionInput.substring(optionInput.indexOf(":") + 1);
		}
		else {
			inputFlag = optionInput;
			inputValue = null;
		}
		
		System.out.println(inputFlag + " ; " + inputValue);
		
		System.out.print("Anagram Solver!  Type a series of letters or -h for help\n\nInput: ");
		
		while(true) {
			String input = reader.readLine();
			
			Processor.process(input);
			
			System.out.print("Input: ");
		}
	}
	
}
