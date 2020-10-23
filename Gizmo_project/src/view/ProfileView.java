package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfileView() {
		setLayout(new BorderLayout());
		
		JPanel myProfilePanel = new JPanel(new GridLayout(4,1));
		myProfilePanel.add(aboutMe());
		myProfilePanel.add(stats());
		setBackground(Color.gray);
		add(myProfilePanel);
		validate();
		
		//myProfilePanel.add(activityOftheMonth);
	}

	
	public JPanel aboutMe() {
		
		JPanel name = new JPanel(new GridLayout(1,3,2,2));
		JPanel labelName = new JPanel(new BorderLayout());
		JPanel weight = new JPanel(new GridLayout(4,1,3,3));
		JPanel age = new JPanel();
		JPanel highScoreLabel = new JPanel();
		
		JButton enterWeight = new JButton("Enter weight...");
		JLabel kilo = new JLabel("Kilo");
		weight.add(kilo,BorderLayout.CENTER);
		weight.add(enterWeight, BorderLayout.WEST);
		
		enterWeight.addActionListener(e -> {
			kilo.setText(JOptionPane.showInputDialog("ENTER YOUR WEIGHT!"));
			});
		
		
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

	
//	JPanel activityOftheMonth = new JPanel();
//	activityOftheMonth.add(month);
//	JLabel month = new JLabel("Month");
//	activityOftheMonth.setBorder(BorderFactory.createTitledBorder("Activity of the Month"));

//	JPanel two = new JPanel();
//	JPanel stats = new JPanel(new BorderLayout());
//
//	myProfilePanel.add(stats);
//	myProfilePanel.add(one);
//	myProfilePanel.add(two);
		
}
