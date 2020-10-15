package view;


import java.awt.Color;

import javax.swing.JFrame;


public class MainFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		super("Gizmo demo");
		setSize(800,800);
        setVisible(true);
        setBackground(Color.darkGray);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(new LoginScreen(this));
        
	}
	

}
