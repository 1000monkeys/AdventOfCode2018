package com.kjellvos.aletho;

import java.util.ArrayList;

public class FabricSpot {
	private ArrayList<Integer> claims = new ArrayList<Integer>();
	
	public ArrayList<Integer> getClaims() {
		return claims;
	}
	
	public void AddClaim(int Id) {
		claims.add(Id);
	}
}
