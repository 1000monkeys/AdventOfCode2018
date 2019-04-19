package com.kjellvos.aletho;

public class Spot {
	private int id, longitude, latitude, width, height;
	
	public Spot(int id, int longitude, int latitude, int width, int height) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.width = width;
		this.height = height;
	}

	public int getId() {
		return id;
	}
	
	public int getLongitude() {
		return longitude;
	}
	
	public int getLatitude() {
		return latitude;	
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
