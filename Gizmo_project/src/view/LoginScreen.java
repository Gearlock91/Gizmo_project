package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;

public class LoginScreen extends JPanel{

	private static final long serialVersionUID = 1L;

    public LoginScreen(Frame frame){
       JPanel p = new JPanel(new BorderLayout(5,5));
        
        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("User Name", SwingConstants.TRAILING));
        labels.add(new JLabel("Password", SwingConstants.TRAILING));
        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField username = new JTextField("Joe Blogs");
        controls.add(username);
        JPasswordField password = new JPasswordField();
       // password.addAncestorListener(p.setFocusable(true));
        controls.add(password);
        p.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
            frame, p, "Log In", JOptionPane.QUESTION_MESSAGE);
        
        add(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
	
}
