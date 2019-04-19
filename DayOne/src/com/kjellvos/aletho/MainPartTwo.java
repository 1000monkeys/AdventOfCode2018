package com.kjellvos.aletho;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainPartTwo {
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		List<Integer> frequencyList = new ArrayList<>();
		List<Integer> frequencyChangesList = new ArrayList<>();
		while (sc.hasNextLine()) {
			int frequencyChange = Integer.parseInt(sc.nextLine());
			frequencyChangesList.add(frequencyChange);
		}
		sc.close();
		
		int i = 0;
		frequencyList.add(0);
		while (i < frequencyChangesList.size()) {
			int newFrequency = frequencyList.get(frequencyList.size() - 1) + frequencyChangesList.get(i);
			
			if (frequencyList.contains(newFrequency)) {
				System.out.println(newFrequency);
				break;
			}else {
				frequencyList.add(newFrequency);	
			}
			
			i++;
			if (frequencyChangesList.size() == i) {
				i = 0;
			}
		}
	}
}