package controller;

import java.util.LinkedList;
import java.util.List;

import dao.ActivityDAO;
import dao.TrackPointDAO;
import data_handler.Import;
import model.Activity;
import model.TrackPoint;

public class FileChooser {
	
	private static FileChooser instance;
	
	private FileChooser() {}
	
	public static FileChooser getInstance() {
		if(instance == null) {
			instance = new FileChooser();
		}
		return instance;
	}

	//----------------------------------------
	
	
	public Activity selectFile(String fileName) {
		List<TrackPoint> list = new LinkedList<TrackPoint>();
		
		list = Import.getInstance(fileName).getList();
		
		Activity a = new Activity(list);
		
		ActivityDAO.getInstance().save(a);
		
		for(int i = 0; i < list.size(); i++)
			TrackPointDAO.getInstance().save(list.get(i));
		return a;
	}
	

	
	
}
