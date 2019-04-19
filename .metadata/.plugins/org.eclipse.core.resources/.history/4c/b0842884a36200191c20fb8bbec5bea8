package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) throws FileNotFoundException {
		Worker[] workers = new Worker[5];
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		boolean[] alphabetBusy = new boolean[alphabet.length];
		HashMap<Character, Node> nodes = new HashMap<Character, Node>();
		
		Scanner scanner = new Scanner(new File("input.txt"));  
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char dependency = line.charAt(5);
			char identifier = line.charAt(36);
			
			if (nodes.containsKey(identifier)) {
				Node node = nodes.get(identifier);
				node.dependencies.add(dependency);
			}else { 
				nodes.put(identifier, new Node(identifier + 60));
				nodes.get(identifier).addToDependencies(dependency);
			}
			
			if (!nodes.containsKey(dependency)) {
				nodes.put(dependency, new Node(dependency + 60));
			}
		}
		scanner.close();
	
		//part 2
		for (int i = 0; i < alphabet.length; i++) {
			alphabetBusy[i] = false;
		}
		
		for (int i = 0; i < 5; i++) {
			workers[i] = new Worker();
		}
		

		int count = 0;
		while (nodes.size() > 0) {		
			for (int i = 0; i < workers.length; i++) {
				if (workers[i].timeLeft > 0) {
					workers[i].timeLeft--;
				}
			}
			for (int i = 0; i < alphabet.length; i++) {
				if (nodes.containsKey(alphabet[i])) {
					if (!nodes.get(alphabet[i]).blockedByDependencies()) {
						for (int j = 0; j < workers.length; j++) {
							if (workers[j].timeLeft == 0 && !alphabetBusy[i] && !workers[j].working) {
								workers[j].stepCode = alphabet[i];
								workers[j].timeLeft = i + 60;
								workers[j].working = true;
								alphabetBusy[i] = true;
								
								i = -1;
								j = 4;
							}
						}
					}
				}
			}
			for (int i = 0; i < workers.length; i++) {
				if (workers[i].timeLeft == 0 && workers[i].working) {
					workers[i].working = false;
					
					for (int k = 0; k < alphabet.length; k++) {
						if (nodes.containsKey(alphabet[k])) {
							nodes.get(alphabet[k]).removeDependencie(workers[i].stepCode);
						}
					}
					nodes.remove(workers[i].stepCode);
				}
			}
			count++;
		}
		System.out.println(count);
	}
}
