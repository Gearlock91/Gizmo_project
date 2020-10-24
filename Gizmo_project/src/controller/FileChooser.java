package controller;

import java.util.LinkedList;
import java.util.List;

import dao.ActivityDAO;
import dao.TrackPointDAO;
import data_handler.Import;
import model.Activity;
import model.TrackPoint;

public class FileChooser {
	
	 //private static FileChooser instance;
	
	//private FileChooser() {}
	
	//public static FileChooser getInstance() {
//		if(instance == null) {
//			instance = new FileChooser();
//		}
//		return instance;
//	}

	//----------------------------------------
	private static List<TrackPoint> list = new LinkedList<TrackPoint>();
	
	public static void selectActivity(String activityName,String profile,String fileName) {

		list.clear();

		
		list = Import.getInstance().readAll(fileName);
		
		Activity a = new Activity(activityName,profile,list);
		
		ActivityDAO.getInstance().add(a);
		ActivityDAO.getInstance().save(a);
		
		
		for(int i = 0; i < list.size(); i++)
			TrackPointDAO.getInstance().save(list.get(i));
		
				
	}
	

	
	
}
