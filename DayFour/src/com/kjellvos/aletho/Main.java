package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		HashMap<Integer, int[]> guards = new HashMap<Integer, int[]>();
		ArrayList<String> actions = new ArrayList<String>();
		ArrayList<Integer> guardIds = new ArrayList<Integer>();
		while (sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			actions.add(nextLine);
			
			String actionString = nextLine.substring(18);
			if (stringContainsNumber(actionString)) {
				int guardId = Integer.parseInt(actionString.replaceAll("\\D", ""));
				
				if (!guardIds.contains(guardId)) {
					guardIds.add(guardId);
				}
				
				guards.put(guardId, new int[60]);
			}
		}
		sc.close();
		Collections.sort(actions);
		
		for (int i = 0; i < actions.size(); i++) {
			System.out.println(actions.get(i));
		}
		
		Collections.sort(guardIds);
		
		int sleepTime = 0;
		int wakeUpTime = 0;
		int guardId = 0;
		for (String action : actions) {
			//String dayString = action.substring(1, 10);
			String timeString = action.substring(12, 17);
			String actionString = action.substring(18);
			
			if (actionString.contains("Guard")) {
				guardId = Integer.parseInt(actionString.replaceAll("\\D", ""));
			}else if (actionString.contains("asleep")) {
				sleepTime = Integer.parseInt(timeString.substring(3, 5));
			}else if (actionString.contains("wakes")) {
				wakeUpTime = Integer.parseInt(timeString.substring(3, 5));
				
				int[] time = guards.get(new Integer(guardId));
				for (int i = sleepTime; i < wakeUpTime; i++) {
					//System.out.println(i);
					time[i]++;
				}
				guards.put(new Integer(guardId), time);
			}
		}
		
		//part1
		guardId = 0;
		int minute = 0;
		int highestMinute = 0;
		int mostMinutesAsleep = 0;
		int guardIdMostMinutesAsleep = 0;
		for (int i = 0; i < guardIds.size(); i++) {
			int[] time = guards.get(guardIds.get(i));
			
			int tempMostMinutesAsleep = 0;
			for (int k = 0; k < time.length; k++) {
				tempMostMinutesAsleep += time[k];
			}
			if (tempMostMinutesAsleep > mostMinutesAsleep) {
				mostMinutesAsleep = tempMostMinutesAsleep;
				guardIdMostMinutesAsleep = guardIds.get(i);
			}
			System.out.println();
		}
		
		int sleepiestMinute = 0;
		int[] time = guards.get(guardIdMostMinutesAsleep);
		for (int j = 0; j < time.length; j++) {
			if (time[j] > highestMinute) {
				highestMinute = time[j];
				sleepiestMinute = j;
			}
		}
		
		System.out.println("For " + mostMinutesAsleep + " minutes guard " + guardIdMostMinutesAsleep + " slept.");
		System.out.println("Minute " + sleepiestMinute + " was guards " + guardIdMostMinutesAsleep + " busiest sleeping minute.");
		System.out.println("The answer is: " + (guardIdMostMinutesAsleep * sleepiestMinute));
		
		
		//part2
		sleepiestMinute = 0;
		int guardIdMostAsleepInOneMinute = 0;
		int tempMostAsleepInOneMinute = 0;
		for (int i = 0; i < guardIds.size(); i++) {
			time = guards.get(guardIds.get(i));
			
			for (int j = 0; j < time.length; j++) {
				if (time[j] > tempMostAsleepInOneMinute) {
					tempMostAsleepInOneMinute = time[j];
					sleepiestMinute = j;
					guardIdMostAsleepInOneMinute = guardIds.get(i);
				}
			}
		}
		
		System.out.println("Guard #" + guardIdMostAsleepInOneMinute + " slept in minute " + sleepiestMinute + " for " + tempMostAsleepInOneMinute + " times.");
		System.out.println("The answer is: " + (guardIdMostAsleepInOneMinute * sleepiestMinute));
	}
	
	public static boolean stringContainsNumber(String string){
	    return Pattern.compile("[0-9]").matcher(string).find();
	}
}