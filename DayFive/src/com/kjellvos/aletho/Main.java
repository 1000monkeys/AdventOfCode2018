package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    static List<Character> units = new ArrayList<Character>();

    public static void main(String[] args) throws FileNotFoundException {
		String input = "";
		Scanner scanner = new Scanner(new File("input.txt"));  
		while (scanner.hasNextLine()) {
			input = scanner.nextLine();
		}
		scanner.close();
    	
        for (String unit : input.split("")) {
            units.add(unit.charAt(0));
        }
		
        //part1
		int i = 0;
        while (i < units.size() - 1) {
            char current = units.get(i);
            char next = units.get(i + 1);
            
            if (Character.isUpperCase(current) != Character.isUpperCase(next) && Character.toUpperCase(current) == Character.toUpperCase(next)) {
                units.remove(i + 1);
                units.remove(i);
                if (i > 0) {i--;}
            } else {
                i++;
            }
        }
		
        System.out.println("Result(size): " + units.size());
        
        //part2
        char[] alphabet = {'a', 'b', 'c', 'd', 'e' ,'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'x'};
        
        int bestSize = Integer.MAX_VALUE;
        for (char letter : alphabet) {
        	String inputWithoutLetter = input.replaceAll("[" + Character.toLowerCase(letter) + Character.toUpperCase(letter) + "]", "");
        	units = new ArrayList<Character>();
        	
            for (String unit : inputWithoutLetter.split("")) {
                units.add(unit.charAt(0));
            }
        	
        	i = 0;
        	while (i < units.size() - 1) {
        		char current = units.get(i);
        		char next = units.get(i + 1);
               
        		if (Character.isUpperCase(current) != Character.isUpperCase(next) && Character.toUpperCase(current) == Character.toUpperCase(next)) {
        			units.remove(i + 1);
        			units.remove(i);
        			if (i > 0) {i--;}
        		} else {
        			i++;
        		}
        	}
        	
        	if (units.size() < bestSize) {
        	   bestSize = units.size();
           }
       }
        System.out.println("Result(size): " + bestSize);

    }
}