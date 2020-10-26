package controller;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import dao.ActivityDAO;
import dao.BicyclingDAO;
import dao.RunDAO;
import dao.TrackPointDAO;
import dao.WalkDAO;
import data_handler.Import;
import model.Activity;
import model.TrackPoint;
import view.MenuScreen;

public class FileChooser implements Runnable{
	
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
		
		switch(String.valueOf(a.getProfile())) {
			case "WALKING":
				new WalkDAO().save(a);
				break;
				
			case "RUNNING":
				new RunDAO().save(a);
				break;
			case "BICYCLING":
				new BicyclingDAO().save(a);
				break;
			default:
				System.out.println("Not a walking activity.");
		}
	
			
			for(int i = 0; i < list.size(); i++)
				TrackPointDAO.getInstance().save(list.get(i));	


				
	}

	@Override
	public void run() {

		while(true) {
			System.out.println("HEJ!");
		}
		
	}

	
}
