package model;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	
	private List<TrackPoint> trackPoints;
	private String activityName;
	
	public Activity(List<TrackPoint> t) {
		trackPoints = t;
	}
	
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
		return trackPoints.get(trackPoints.size() - 1).getDistance();
	}
	
	public int getAvgHeartRate() {
		double sumHeartRate = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			sumHeartRate += trackPoints.get(i).getHart();
		}
		return (int) (sumHeartRate / trackPoints.size());
	}
	
	private int getDiff(String start, String end ,int posS, int posE) {
		int End = Integer.parseInt(end.substring(posS, posE));
		int Start = Integer.parseInt(start.substring(posS, posE));
		
		if(Start > End)
			return Start - End;
		else
			return End - Start;
	}
	
	public String getTime() {
		
		String startTime = trackPoints.get(0).getTime();
		String endTime	 = trackPoints.get(trackPoints.size() - 1).getTime();
		
		int timeHH = getDiff(startTime, endTime,0, 2);
		int timeMM = getDiff(startTime, endTime,3, 5);
		int timeSS = getDiff(startTime, endTime,6, 8); 
		
		return String.format("%02d:%02d:%02d", timeHH, timeMM, timeSS);
	}

	public double getAvgCadance() {
		double cadance = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			cadance += trackPoints.get(i).getCadence();
		}
		return (cadance / trackPoints.size());
	}
	public int size() {
		return trackPoints.size();
	}
	
	public void setName(String name) {
		activityName = name;
	}
	
	public List<TrackPoint> getActivity(){
		return trackPoints;
	}
	
	@Override
	public String toString() {
		return "Activity: " + activityName + "\n" 
			   + getAvgHeartRate()  +"\n"+ 
				 getDistance() 		+"\n"+
				 getAvgCadance() 	+"\n"+ 
				 getTime();
	}

}
