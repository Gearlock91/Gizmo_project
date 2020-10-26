package model;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	
	private List<TrackPoint> trackPoints = new LinkedList<TrackPoint>();
	private enum ActivityProfile {WALKING,RUNNING,BICYCLING};
	private ActivityProfile profile;
	private String activityName;
	
	/**/
	
	public Activity(String activityName , String profile,List<TrackPoint> t) {
		this.activityName = activityName;
		
		System.out.println(profile);
		
		switch(profile) {
			case "WALKING":
				this.profile = ActivityProfile.WALKING;
				break;
			case "RUNNING":
				this.profile = ActivityProfile.RUNNING;
				break;
			case "BICYCLING":
				this.profile = ActivityProfile.BICYCLING;
				break;
			default:
				System.err.println("ERROR NO ACTIVITY TYPE");
				break;
		}
		
		trackPoints.addAll(t);
	
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
	
	public double getMaxHeartActivity() {
		
		double maxHeart = trackPoints.get(0).getHeart();
		
		for(int i = 0; i < trackPoints.size(); i++) {
			if(maxHeart < trackPoints.get(i).getHeart())
				maxHeart = trackPoints.get(i).getHeart();
		}
		return maxHeart;
	}
	public double getMinHeart() {
		
		double minHeart = trackPoints.get(0).getHeart();
		
		for(int i = 0; i < trackPoints.size(); i++) {
			if(minHeart > trackPoints.get(i).getHeart())
				minHeart = trackPoints.get(i).getHeart();
		}
		return minHeart;
	}
	
	public int getAvgHeartRate() {
		double sumHeartRate = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			sumHeartRate += trackPoints.get(i).getHeart();
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
	
	public String getStartTime() {
		return trackPoints.get(0).getTime();
	}
	public String getEndTime() {
		return trackPoints.get(trackPoints.size() - 1).getTime();
	}
	
	public String getTotalTime() {
		
		int timeHH = getDiff(getStartTime(), getEndTime(),0, 2);
		int timeMM = getDiff(getStartTime(), getEndTime(),3, 5);
		int timeSS = getDiff(getStartTime(), getEndTime(),6, 8); 
		
		return String.format("%02d:%02d:%02d", timeHH, timeMM, timeSS);
	}

	public double getAvgCadance() {
		double cadance = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			cadance += trackPoints.get(i).getCadence();
		}
		return (cadance / trackPoints.size());
	}
	
	public double getMaxSpeed() {
		
		double maxSpeed = trackPoints.get(0).getSpeed();
		
		for(int i = 0; i < trackPoints.size(); i++) {
			if(maxSpeed < trackPoints.get(i).getSpeed())
				maxSpeed = trackPoints.get(i).getSpeed();
		}
		return maxSpeed;
	}
	
	public double getAvgSpeed() {
		double speed = 0;
		for(int i = 0; i < trackPoints.size(); i++) {
			speed += trackPoints.get(i).getSpeed();
		}
		return (speed / trackPoints.size());
	}
	
	
	
	public int size() {
		return trackPoints.size();
	}
	
	public void setName(String name) {
		activityName = name;
	}
	
	public String getName() {
		return activityName;
	}
	
	public List<TrackPoint> getActivity(){
		return trackPoints;
	}
	
	public void setProfile(ActivityProfile profile) {
		this.profile = profile;
	}
	
	public ActivityProfile getProfile() {
		return profile;
	}
	
	@Override
	public String toString() {
		return "Activity: " + activityName + "\n" 
			   + getAvgHeartRate()  +"\n"+ 
				 getDistance() 		+"\n"+
				 getAvgCadance() 	+"\n"+ 
				 getTotalTime();
	}

}
