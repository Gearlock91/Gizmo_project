package model;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	
	private List<TrackPoint> trackPoints;
//	private String activityName;
	
	
	
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
	
	public String getTime() {
//		int timehh = 0;
//		int timemm = 0;
//		int timess = 0; 
		String startTime = trackPoints.get(0).getTime();
		String endTime	 = trackPoints.get(trackPoints.size() - 1).getTime();
		
//		timehh += Integer.parseInt(endTime.substring(0, 2)) - Integer.parseInt(startTime.substring(0, 2));
//		timemm += Integer.parseInt(endTime.substring(3, 5)) - Integer.parseInt(startTime.substring(3, 5));		
//		timess += Integer.parseInt(endTime.substring(6, 8)) - Integer.parseInt(startTime.substring(6, 8));
		
		return String.format("%02d:%02d:%02d", 
							Integer.parseInt(endTime.substring(0, 2)) - Integer.parseInt(startTime.substring(0, 2)), 
							Integer.parseInt(endTime.substring(3, 5)) - Integer.parseInt(startTime.substring(3, 5)), 
							Integer.parseInt(endTime.substring(6, 8)) - Integer.parseInt(startTime.substring(6, 8)));
	}
	
	public void getAvgCadance() {
		
	}
	public int size() {
		return trackPoints.size();
	}

}
