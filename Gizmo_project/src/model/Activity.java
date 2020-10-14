package model;

import java.util.List;

public class Activity {
	
	private List<TrackPoint> trackPoints;
	private String activityName;
	private double durotation;
	
	
	
	public Activity (String name) {
		activityName = name;
	}
	
	public void addPoint(TrackPoint p) {
		trackPoints.add(p);
	}
	
	public void getDistance() {
		
	}
	
	public void getAvgHeartRate() {
		
	}
	
	public double getTime() {
		return durotation;
	}
	
	public void getAvgCadance() {
		
	}
}
