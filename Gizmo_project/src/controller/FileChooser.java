package controller;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dao.ActivityDAO;
import dao.BicyclingDAO;
import dao.RunDAO;
import dao.TrackPointDAO;
import dao.WalkDAO;
import data_handler.Import;
import model.Activity;
import model.TrackPoint;

public class FileChooser implements Runnable{
	
	private static FileChooser instance;
	
	private FileChooser() {}
	
	public static FileChooser getInstance() {
		if(instance == null) {
			instance = new FileChooser();
		}
		return instance;
	}
	//---------------------------------------- 
	private List<TrackPoint> list = new LinkedList<TrackPoint>();
	private JFrame frame;
	
	public void selectActivity(JFrame frame,String activityName,String profile,String fileName) {
		this.frame = frame;
		Thread th = new Thread(this);	
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
				System.out.println("Not a activity.");
		}
		
		th.start();
		JOptionPane.showMessageDialog(frame, "Synchronizing, this may take a while...");
	}

	@Override
	public void run() {
	
		
		for(int i = 0; i < list.size(); i++)
			TrackPointDAO.getInstance().save(list.get(i));	
		JOptionPane.showMessageDialog(frame, "Done");
	}

	
}
