package com.anagram.solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.anagram.solver.helper.Processor;

public class Runner {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Anagram Solver!  Type a series of letters or -h for help\n\nInput: ");
		
		while(true) {
			String input = reader.readLine();
			
			Processor.process(input);
			
			System.out.print("Input: ");
		}
	}
	
}
