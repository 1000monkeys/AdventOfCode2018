package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	private static ArrayList<Integer> numbers = new ArrayList<Integer>();
	private static int metadataSum = 0;
	private static int metadataSumTwo = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] numberArray = sc.nextLine().split(" ");
			for (int i = 0; i < numberArray.length; i++) {
				numbers.add(Integer.parseInt(numberArray[i]));
			}
		}
		sc.close();
		
		readHeader();
		
		System.out.println(metadataSum);
		System.out.println(metadataSumTwo);
	}
	
	public static void readHeader() {
		int amountChildren = numbers.get(0);
		int amountMetadata = numbers.get(1);
		numbers.remove(0);
		numbers.remove(0);
	
		for (int i = 0; i < amountChildren; i++) {
			readHeader();
		}
		
		for (int i = 0; i < amountMetadata; i++) {
			metadataSum += numbers.get(0);
			numbers.remove(0);
		}
	}
}
