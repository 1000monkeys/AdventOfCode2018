package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<int[]> points = new ArrayList<int[]>();
	static int maxX = 0, maxY = 0;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner scanner = new Scanner(new File("input.txt"));  
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			int x = Integer.parseInt(line.split(", ")[0]);
			int y = Integer.parseInt(line.split(", ")[1]);
			
			points.add(new int[2]);
			points.get(points.size() - 1)[0] = x;
			points.get(points.size() - 1)[1] = y;
			
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
		}
		scanner.close();
		
		int grid[][][] = new int[maxX + 1][maxY + 1][2];
		for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j][1] = Integer.MAX_VALUE;
            }
		}
		
		//part1
		for (int i = 0; i < points.size(); i++) {
			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[x].length; y++) {
					int currentDistance = getManhattanDistance(points.get(i), new int[] {x, y});
					if (grid[x][y][1] == currentDistance) {
						grid[x][y][0] = -1;
					}else if (grid[x][y][1] > currentDistance) {
						grid[x][y][1] = currentDistance;
						grid[x][y][0] = i;
					}
				}
			}
		}
		
		int maxAreaSize = 0;
		for (int i = 0; i < points.size(); i++) {
			boolean isInfinite = false;
			int currentAreaSize = 0;
			
			for (int x = 0; x < grid.length && !isInfinite; x++) {
				for (int y = 0; y < grid[x].length && !isInfinite; y++) {
					if ((y == 0 || y == grid[y].length - 1 ||
						 x == 0 || x == grid[x].length - 1)
							&& grid[x][y][0] == i) {
						isInfinite = true;
					}else if (grid[x][y][0] == i) {
						currentAreaSize++;
					}
				}
			}
			if(!isInfinite) {
                maxAreaSize = Math.max(maxAreaSize, currentAreaSize);
			}
		}
		System.out.println("Result: " + maxAreaSize);
		
		//part2
		int[][] gridTwo = new int[maxX + 1][maxY + 1];
		for (int i = 0; i < points.size(); i++) {
			for (int x = 0; x < maxX; x++) {
				for (int y = 0; y < maxY; y++) {
					gridTwo[x][y] += getManhattanDistance(points.get(i), new int[] {x, y});
				}
			}
		}
		
		int size = 0;
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				if (gridTwo[x][y] < 10000) {
					size++;
				}
			}
		}
		System.out.println("Result: " + size);
	}
	
	public static int getManhattanDistance(int[] point1, int[] point2) {
		 return Math.abs(point1[0]-point2[0]) + Math.abs(point1[1]-point2[1]);
	}
}
