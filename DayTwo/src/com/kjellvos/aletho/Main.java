package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		List<String> IDList = new ArrayList<>();
		while (sc.hasNextLine()) {
			IDList.add(sc.nextLine());
		}
		sc.close();

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int doubles = 0;
		int triples = 0;
		for (String temp : IDList) {
			HashMap<String, Integer> set = new HashMap<String, Integer>();
			
			for (int i = 0; i < temp.length(); i++) {
				if (set.containsKey(temp.charAt(i) + "")) {
					set.put(temp.charAt(i) + "", set.get(temp.charAt(i) + "") + 1);
				}else {
					set.put(temp.charAt(i) + "", 1);
				}
			}
			
			System.out.println(set);
			
			boolean doubleFound = false;
			boolean tripleFound = false;
			for (int j = 0; j < alphabet.length(); j++) {
				if (set.containsKey(alphabet.charAt(j) + "")) {
					if (set.get(alphabet.charAt(j) + "") == 2 && doubleFound == false) {
						doubleFound = true;
						doubles++;
					}
					if (set.get(alphabet.charAt(j) + "") == 3 && tripleFound == false) {
						tripleFound = true;
						triples++;
					}
				}
			}
		}
		
		System.out.println("Doubles: " + doubles + ", Triples: " + triples + ", Checksum = " + (doubles * triples));
	}
	
	
}