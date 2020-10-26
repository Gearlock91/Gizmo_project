package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import javax.crypto.Mac;
import javax.imageio.ImageIO;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.FileChooser;
import dao.ActivityDAO;
import dao.TrackPointDAO;
import dao.UserListDAO;
import mainApp.App;
import model.Activity;
import model.TrackPoint;
import model.User;


public class MenuScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	int j = 0;
	JFrame frame;
	User active = null;
	
	JTabbedPane holder = new JTabbedPane();
	public MenuScreen(JFrame frame) {
		this.setLayout(new BorderLayout());
		this.frame = frame;
		
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Clock");
		JMenu activity = new JMenu("Activities");
		JMenu profile = new JMenu("Profile");
		JMenuItem openFile = new JMenuItem("Read CSV file...");
		JMenuItem logout = new JMenuItem("LogOut");
		JMenuItem myProfile = new JMenuItem("Profile");
		
		activity.removeAll();
	
		fetchTP();
		createMenuItems(activity);
			
		myProfile.addActionListener(e -> {showMyProfile();});
		openFile.addActionListener(e -> {
			
			String[] activities = {"WALKING", "RUNNING", "BICYCLING"};
			
			JPanel mainFileChooser = new JPanel(new BorderLayout());
			JPanel fieldBoxOfDoom = new JPanel(new GridLayout(3,1));
			JTextField activityName = new JTextField("Choose activity name");
			JComboBox<String> activityProfile = new JComboBox<String>(activities);
			JTextField selectFile = 	new JTextField("Press enter...");
			
		
			selectFile.addActionListener(e2 -> {
				JFileChooser chooser = new JFileChooser();
				
				chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = chooser.showOpenDialog(this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {selectFile.setText((chooser.getSelectedFile().toPath().toString()));}
			});
		
			
			
			
			fieldBoxOfDoom.add(activityName);
			fieldBoxOfDoom.add(activityProfile);
			fieldBoxOfDoom.add(selectFile);
			mainFileChooser.add(fieldBoxOfDoom);
			
			
			int valueR = JOptionPane.showConfirmDialog(frame,mainFileChooser, "Import...", JOptionPane.OK_CANCEL_OPTION , 2);
			if(valueR == JOptionPane.OK_OPTION) {
				 String getActivityProfile = String.valueOf(activityProfile.getSelectedItem());
				 FileChooser.getInstance().selectActivity(frame,activityName.getText(),getActivityProfile,selectFile.getText());
				 activity.removeAll();
				 createMenuItems(activity);
				 revalidate();
				 repaint();
				 
			}
		});
		
		logout.addActionListener(e -> {
			for(User a : UserListDAO.getInstance())
				if(a.getActive())
					active = a;
			UserListDAO.getInstance().update(active);
			UserListDAO.getInstance().clear();
			ActivityDAO.getInstance().clear();
	    	TrackPointDAO.getInstance().clear();
			frame.removeAll();
			frame.validate();
			frame.dispose();
			SwingUtilities.invokeLater(() -> new MainFrame());
			
		});
		
		file.add(openFile);
		
		profile.add(myProfile);
		profile.add(logout);
		
		
		bar.add(file);
		bar.add(activity);
		bar.add(profile);
		return bar;
	}
	
	public JPanel createData(Activity aktivitet) {
		JPanel panel = new JPanel(new GridLayout(7,1));
		JLabel hrate = new JLabel("Average heart rate: " + String.valueOf(aktivitet.getAvgHeartRate()) + "Bpm");
		JLabel distance = new JLabel("Distance: " +String.valueOf(aktivitet.getDistance()) + " meter");
		JLabel cadence = new JLabel("Cadence:  " +String.valueOf(aktivitet.getAvgCadance()));
		JLabel time = new JLabel("Total time: " +aktivitet.getTotalTime());
		JLabel start = new JLabel("Start time: " + String.valueOf(aktivitet.getStartTime()));
		JLabel end	= new JLabel("End Time:" + aktivitet.getEndTime());
		JLabel maxHeart = new JLabel("Max Heart rate: " + String.valueOf(aktivitet.getMaxHeartActivity() + "Bpm"));
		JLabel minHeart = new JLabel("Min Heart rate: " + String.valueOf(aktivitet.getMinHeart() + "Bpm"));
		JLabel avgSpeed = new JLabel("Average speed: " + String.valueOf(aktivitet.getAvgSpeed()  + "km/h"));
		JLabel maxSpeed = new JLabel("Max speed: " + String.valueOf(aktivitet.getMaxSpeed()  + "km/h"));
		
		
		panel.add(distance);
		panel.add(cadence);
		panel.add(time);
		panel.add(start);
		panel.add(end);
		panel.add(maxHeart);
		panel.add(minHeart);
		panel.add(hrate);
		panel.add(avgSpeed);
		panel.add(maxSpeed);
		return panel;
	}
	
	public JPanel createGraphs(Activity aktivitet) {
		JPanel panel = new JPanel(new GridLayout(4, 1));
		
		panel.add(new PlotView("HR", aktivitet, tp -> tp.getHeart()));
		panel.add(new PlotView("Altitude", aktivitet, tp -> tp.getAlt()));
		panel.add(new PlotView("Speed", aktivitet, tp -> tp.getSpeed()));
		panel.add(new PlotView("Cadence", aktivitet, tp -> tp.getCadence()));

		return panel;
	}
	
	public void addAction(JMenuItem items, int i) {
		items.addActionListener(e -> {
			this.removeAll();
			holder.removeAll();
			holder.addTab("Super data", createData(ActivityDAO.getInstance().get(i)));
			holder.addTab("Graph", createGraphs(ActivityDAO.getInstance().get(i)));
			holder.addTab("Map", createMap(ActivityDAO.getInstance().get(i)));
			this.add(holder);
			revalidate();
		
		});
	}
	
	public void createMenuItems(JMenu activity) {
		JMenuItem items[] = new JMenuItem[ActivityDAO.getInstance().size()];
		
		for(int i = 0; i < ActivityDAO.getInstance().size(); i++) {
			items[i] = new JMenuItem(ActivityDAO.getInstance().get(i).getName());
			activity.add(items[i]);	
			addAction(items[i], i);
		}
	}
	
	public void fetchTP() {	
		ActivityDAO.getInstance().getAll();
	}
	
	
	public JPanel createMap(Activity a) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setSize(50, 50);
		panel.add(new PlotMapView(a));
		return panel;
		
	}
	
	public void showMyProfile() {
		removeAll();
		this.add(new ProfileView());
		validate();
	}
	
	 public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	try {
				Image img = ImageIO.read(new File("Assets/Background.jpg"));
				g.drawImage(img, 0,0, getWidth(),getHeight(),this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
