package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainPartTwo {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		List<String> IDList = new ArrayList<>();
		while (sc.hasNextLine()) {
			IDList.add(sc.nextLine());
		}

		
		for (int i = 0; i < IDList.size(); i++) {
			for (int j = 0; j < IDList.size(); j++) {
				if (levenshteinDistance(IDList.get(i), IDList.get(j)) == 1) {
					System.out.println(IDList.get(i) + " - " + IDList.get(j));
				}
			}
		}
		sc.close();
	}

	public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
	    int len0 = lhs.length() + 1;                                                     
	    int len1 = rhs.length() + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings        
	    return cost[len0 - 1];                                                          
	}
}
