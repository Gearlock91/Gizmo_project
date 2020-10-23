package model;

import java.util.List;

public class TrackPoint {

	private int aId;
	private String date;
	private String time;
	private int elapsedTime;
	private double latitude;
	private double longitude;
	private double altitude;
	private double distance;
	private double heartRate;
	private double speed;
	private double cadence;

	
	public TrackPoint(int i,List<String> t) {
		date = t.get(0);
		time = t.get(1);
		elapsedTime = Integer.parseInt(t.get(2));
		latitude = Double.parseDouble(t.get(3));
		longitude = Double.parseDouble(t.get(4));
		altitude = Double.parseDouble(t.get(5));
		distance = Double.parseDouble(t.get(6));
		heartRate = Double.parseDouble(t.get(7));
		speed = Double.parseDouble(t.get(8));
		cadence = Double.parseDouble(t.get(9));
		
		
	}
	
	public TrackPoint(List<String> t) {
		date = t.get(0);
		time = t.get(1);
		elapsedTime = Integer.parseInt(t.get(2));
		latitude = Double.parseDouble(t.get(3));
		longitude = Double.parseDouble(t.get(4));
		altitude = Double.parseDouble(t.get(5));
		distance = Double.parseDouble(t.get(6));
		heartRate = Double.parseDouble(t.get(7));
		speed = Double.parseDouble(t.get(8));
		cadence = Double.parseDouble(t.get(9));
		aId = Integer.parseInt(t.get(10));
		
	}
	
	public TrackPoint(TrackPoint t) {
		date = t.getDate();
		time = t.getTime();
		elapsedTime = t.getElapsedTime();
		latitude = t.getLat();
		longitude = t.getLong();
		altitude = t.getAlt();
		distance = t.getDistance();
		heartRate = t.getHart();
		speed = t.getSpeed();
		cadence = t.getCadence();
	}
	
	public String getDate() {
		return date;
		
	}
	public String getTime() {
		return time;
		
	}
	public int getElapsedTime() {
		return elapsedTime;
		
	}
	public double getLat() {
		return latitude;
		
	}
	public double getLong() {
		return longitude;
		
	}
	public double getAlt() {
		return altitude;
		
	}
	public double getDistance() {
		return distance;
		
	}
	public double getHart() {
		return heartRate;
		
	}
	public double getSpeed() {
		return speed;
		
	}
	public double getCadence() {
		return cadence;
		
	}
	
	public int getAID() {
		return aId;
	}
	
	@Override
	public String toString() {
		return date + " " + time + " " + elapsedTime + " " + 
			   latitude + " " + longitude + " " + altitude + 
			   " " + distance + " " + heartRate + " " + speed 
			   + " " + cadence;
	}
}
