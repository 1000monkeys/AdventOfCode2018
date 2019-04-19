package com.kjellvos.aletho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner sc = new Scanner(file);
		
		
		ArrayList<Spot> spots = new ArrayList<Spot>();
		final Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		
		int widthOfFabric = 0;
		int heightOfFabric = 0;
		while (sc.hasNextLine()) {
			String inputString = sc.nextLine();
			Matcher matcher = pattern.matcher(inputString);
			
			if (matcher.matches()) {
				int id = Integer.parseInt(matcher.group(1));
				int longitude = Integer.parseInt(matcher.group(2));
				int latitude = Integer.parseInt(matcher.group(3));
				int width = Integer.parseInt(matcher.group(4));
				int height = Integer.parseInt(matcher.group(5));
				
				spots.add(new Spot(id, longitude, latitude, width, height));
				
				if (heightOfFabric < latitude + width) {
					heightOfFabric = latitude + width;
				}
				if (widthOfFabric < longitude + height) {
					widthOfFabric = longitude + height;
				}
			}
		}
		sc.close();
		
		System.out.println("Height: " + heightOfFabric + " ,Width: " + widthOfFabric);
		FabricSpot[][] fabric = new FabricSpot[heightOfFabric][widthOfFabric];		
		
		for (int h = 0; h < heightOfFabric; h++) {
			for (int w = 0; w < widthOfFabric; w++) {
				fabric[h][w] = new FabricSpot();
			}
		}
		
		for (int i = 0; i < spots.size(); i++) {
			Spot currentPiece = spots.get(i);
			for (int h = currentPiece.getLatitude(); h < currentPiece.getHeight() + currentPiece.getLatitude(); h++) {
				for (int w = currentPiece.getLongitude(); w < currentPiece.getWidth() + currentPiece.getLongitude(); w++) {			
					fabric[h][w].AddClaim(currentPiece.getId());
				}
			}
		}
		
		int multipleClaimSpots = 0;
		for (FabricSpot[] fabricSpotWidthArray : fabric) {
			for (FabricSpot fabricSpot : fabricSpotWidthArray) {
				if (fabricSpot.getClaims().size() > 1) {
					multipleClaimSpots++;
				}
			}
		}
		
		System.out.println(multipleClaimSpots);
		
		
		//part 2
		for (int i = 0; i < spots.size(); i++) {
			Spot spot = spots.get(i);
			int size = spot.getWidth() * spot.getHeight();
			
			for (int h = spot.getLatitude(); h < spot.getLatitude() + spot.getHeight(); h++) {
				for (int w = spot.getLongitude(); w < spot.getLongitude() + spot.getWidth(); w++) {
					if (fabric[h][w].getClaims().size() == 1) {
						size--;
					}
				}
			}
			
			if (size == 0) {
				System.out.println(spot.getId());
				break;
			}
		}
	}
}
