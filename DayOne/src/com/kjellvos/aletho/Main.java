package com.kjellvos.aletho;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		int frequency = 0;
		while (sc.hasNextLine()) {
			int frequencyChange = Integer.parseInt(sc.nextLine());
			frequency = frequency + frequencyChange;
		}
		
		sc.close();
		System.out.println(frequency);
	}
}
