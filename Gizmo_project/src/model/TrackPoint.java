package model;



public class TrackPoint {

	private String tPoint;
	private String date;
	private String time;
	private int elapsedTime;
	private double latitude;
	private double longitude;
	private double altitude;
	private double distance;
	private int heartRate;
	private double speed;
	private double cadence;

	
	public TrackPoint(String[] d) {
		
		date = d[0];
		time = d[1];
		elapsedTime = Integer.parseInt(d[2]);
		latitude = Double.parseDouble(d[3]);
		longitude = Double.parseDouble(d[4]);
		altitude = Double.parseDouble(d[5]);
		distance = Double.parseDouble(d[6]);
		heartRate = Integer.parseInt(d[7]);
		speed = Double.parseDouble(d[8]);
		cadence = Double.parseDouble(d[9]);
		
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
	public int getHart() {
		return heartRate;
		
	}
	public double getSpeed() {
		return speed;
		
	}
	public double getCadence() {
		return cadence;
		
	}
}
