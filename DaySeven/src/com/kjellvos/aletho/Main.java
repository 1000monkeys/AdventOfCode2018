package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
	static HashMap<Character, Node> nodes = new HashMap<Character, Node>();
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("input.txt"));  
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char dependency = line.charAt(5);
			char identifier = line.charAt(36);
			
			if (nodes.containsKey(identifier)) {
				Node node = nodes.get(identifier);
				node.dependencies.add(dependency);
			}else { 
				nodes.put(identifier, new Node());
				nodes.get(identifier).addToDependencies(dependency);
			}
			
			if (!nodes.containsKey(dependency)) {
				nodes.put(dependency, new Node());
			}
		}
		scanner.close();
		
		//part 1
		String output = "";
		while (nodes.size() > 0) {
			for (int i = 0; i < alphabet.length; i++) {
				if (nodes.containsKey(alphabet[i])) {
					if (!nodes.get(alphabet[i]).blockedByDependencies()) {
						output = output + alphabet[i];
						removeDependencies(alphabet[i]);
						nodes.remove(alphabet[i]);
						i = -1;
					}
				}
			}
		}
		System.out.println(output);
	}
	
	public static void removeDependencies(char dependency) {
		for (int i = 0; i < alphabet.length; i++) {
			if (nodes.containsKey(alphabet[i])) {
				nodes.get(alphabet[i]).removeDependencie(dependency);
			}
		}
	}
}
