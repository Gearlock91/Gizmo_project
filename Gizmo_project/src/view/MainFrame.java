package view;

import java.awt.Color;
import java.sql.Time;
import java.util.Timer;

import javax.swing.JFrame;

import controller.FileChooser;
import dao.ActivityDAO;
import dao.TrackPointDAO;
import dao.UserListDAO;


public class MainFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		super("Gizmo demo");
		setSize(800,800);
        setVisible(true);
        setBackground(Color.darkGray);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        LoginScreen logInSc = new LoginScreen(this);
        MenuScreen mScreen = new MenuScreen(this);
        
        setJMenuBar(mScreen.createMenuBar());
       
        
        add(logInSc);
        
        if(logInSc.loginSuccessfull()) {	
        	remove(logInSc);
        	add(mScreen);
        	validate();
        }
        
        
	}

}
