package model;

import java.util.List;

public class TrackPoint {

	private String tPoint;
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

	
	public TrackPoint(List<String> d) {
		
		date = d.get(0);
		time = d.get(1);
		elapsedTime = Integer.parseInt(d.get(2));
		latitude = Double.parseDouble(d.get(3));
		longitude = Double.parseDouble(d.get(4));
		altitude = Double.parseDouble(d.get(5));
		distance = Double.parseDouble(d.get(6));
		heartRate = Double.parseDouble(d.get(7));
		speed = Double.parseDouble(d.get(8));
		cadence = Double.parseDouble(d.get(9));
		
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
	
	@Override
	public String toString() {
		return date + " " + time + " " + elapsedTime + " " + 
			   latitude + " " + longitude + " " + altitude + 
			   " " + distance + " " + heartRate + " " + speed 
			   + " " + cadence;
	}
}
