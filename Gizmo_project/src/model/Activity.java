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
	
	public double getDistance() {
		double distance = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			distance += trackPoints.get(i).getDistance();
		}
		
		return distance;
	}
	
	public int getAvgHeartRate() {
		double sumHeartRate = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			sumHeartRate += trackPoints.get(i).getHart();
		}
		return (int) (sumHeartRate / trackPoints.size());
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
