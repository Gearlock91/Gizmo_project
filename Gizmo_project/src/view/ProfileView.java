package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.UserListDAO;
import model.User;

public class ProfileView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	User activeUser = null;
	
	public ProfileView() {
		UserListDAO.getInstance().getAll();
		
		setLayout(new BorderLayout());
		
		
		JPanel myProfilePanel = new JPanel(new GridLayout(4,1));
		myProfilePanel.setOpaque(false);
		myProfilePanel.add(aboutMe());
		//myProfilePanel.add(stats());
		add(myProfilePanel, BorderLayout.CENTER);
		validate();
		
		//myProfilePanel.add(activityOftheMonth);
	}

	
	public JPanel aboutMe() {
		
		JPanel name = new JPanel(new GridLayout(1,3,2,2));
		JPanel labelName = new JPanel(new GridLayout(4,1,3,3));
		JPanel weight = new JPanel(new GridLayout(4,1,3,3));
		JPanel age = new JPanel(new GridLayout(4,1,3,3));
		JPanel highScoreLabel = new JPanel(new GridLayout(4,1,3,3));
		
		
		
		for(User a : UserListDAO.getInstance())
			if(a.getActive())
				activeUser = a;
		
		JLabel fullName = new JLabel(activeUser.getName());
		
		JButton enterWeight = new JButton("Enter weight...");
		JLabel kilo = new JLabel(String.valueOf(activeUser.getWeight()) + " kg");
		
		JButton enterAge = new JButton("Enter age...");
		JLabel ageRepresentation = new JLabel(String.valueOf(activeUser.getAge() + " Years"));
		enterAge.addActionListener(e -> {
			ageRepresentation.setText(JOptionPane.showInputDialog("Enter age"));
			activeUser.setAge(Integer.valueOf(ageRepresentation.getText()));
			
		});
		
	
		weight.add(kilo,BorderLayout.CENTER);
		weight.add(enterWeight, BorderLayout.WEST);
		
		enterWeight.addActionListener(e -> {
			kilo.setText(JOptionPane.showInputDialog("ENTER YOUR WEIGHT!"));
			activeUser.setWeight(Double.valueOf(kilo.getText()));
			});
		
		
		JLabel maxPuls = new JLabel("Max heart rate: " + String.valueOf(activeUser.getMaxHeart()) + " Bpm" ) ;
		JLabel maxDist = new JLabel("Max distance: "   + String.valueOf(activeUser.getMaxDistance()) + " Meters");
		
		JLabel height = new JLabel(String.valueOf(activeUser.getHeight()) + " cm");
		JButton setHeight = new JButton("Set height");
		
		setHeight.addActionListener(e -> {
			height.setText(JOptionPane.showInputDialog("Set height..") );
			activeUser.setHeight(Double.valueOf(height.getText()));
		});
		
		highScoreLabel.add(height);
		highScoreLabel.add(setHeight);
		
		
		age.add(ageRepresentation);
		age.add(enterAge);
		
		labelName.add(fullName, BorderLayout.CENTER);
		labelName.add(maxPuls);
		labelName.add(maxDist);
		
		name.add(labelName);
		name.add(weight);
		name.add(age);
		name.add(highScoreLabel);
		name.setBorder(BorderFactory.createTitledBorder("Me"));
		return name;
	}
	
	public JPanel stats() {
		JPanel highScore = new JPanel(new GridLayout(1,3));
		highScore.setBorder(BorderFactory.createTitledBorder("Best activities"));
		JPanel walking = new JPanel();
		JPanel running = new JPanel();
		JPanel bycyicling = new JPanel();
		walking.setBorder(BorderFactory.createTitledBorder("Walking"));
		running.setBorder(BorderFactory.createTitledBorder("Running"));
		bycyicling.setBorder(BorderFactory.createTitledBorder("CYKLA"));
		
		highScore.add(walking);
		highScore.add(running);
		highScore.add(bycyicling);
		return highScore;
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
