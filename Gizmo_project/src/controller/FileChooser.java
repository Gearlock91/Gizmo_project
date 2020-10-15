package controller;

import java.util.LinkedList;
import java.util.List;

import data_handler.Import;
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
	
	
	public void selectFile(String fileName) {
		List<TrackPoint> list = new LinkedList<TrackPoint>();
		
		list = Import.getInstance(fileName).getList();
		
		for(TrackPoint a: list)
			System.out.println(a);
	}
	

	
	
}
