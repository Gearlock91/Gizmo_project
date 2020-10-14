package model;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	
	private List<TrackPoint> trackPoints;
//	private String activityName;
	private double durotation;
	
	
	
	public Activity () {
		trackPoints = new LinkedList<TrackPoint>();
	}
	
	public void addPoint(TrackPoint p) {
		trackPoints.add(p);
	}
	
	public TrackPoint getPoint(int i) {
		return trackPoints.get(i);
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
	public int size() {
		return trackPoints.size();
	}
}
