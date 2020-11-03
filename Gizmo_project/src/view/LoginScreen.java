package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.Arrays;

import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.RegisterUser;
import dao.ActivityDAO;
import dao.TrackPointDAO;
import dao.UserListDAO;
import model.User;

public class LoginScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean succecfullLogin = false;

    public LoginScreen(JFrame frame){
    	JPanel p = new JPanel(new BorderLayout(5,5));
        JPanel labels = new JPanel(new GridLayout(3,1,2,2));
        labels.add(new JLabel("User Name", SwingConstants.TRAILING));
        labels.add(new JLabel("Password", SwingConstants.TRAILING));
        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(3,1,2,2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        JButton register = new JButton("Register");
        
        register.addActionListener(e -> {createRegisterPanel(frame,p);});
        
        controls.add(register);
        p.add(controls, BorderLayout.CENTER);

        logIn(frame,p,username,password);
        
    }
    
    public void logIn(JFrame frame, JPanel p, JTextField userName, JPasswordField password) {
    	int opt = JOptionPane.showConfirmDialog(frame, p, "Log In", JOptionPane.OK_CANCEL_OPTION);
    	UserListDAO.getInstance().getAll();

    	switch(opt) {
    	case JOptionPane.OK_OPTION:
    		if(isUserNameCorrect(userName.getText()) && isPasswordCorrect(password.getPassword())){
            	JOptionPane.showMessageDialog(frame, "Success!");
            	succecfullLogin = true;
            	break;
              } 
            else {
            	JOptionPane.showMessageDialog(this, "Inccorect password or no user with that name");
            	logIn(frame,p,userName,password);
            	break;
            }   	
    	case JOptionPane.CANCEL_OPTION:
    		System.exit(0);
    		break;
    	case -1:
    		System.exit(0);
    	}		         
    }
    
    public boolean loginSuccessfull() {
    	return succecfullLogin;
    }
  
    
    public boolean isUserNameCorrect(String userName) {
    	for(User a : UserListDAO.getInstance()) {
    		if(a.getUserName().equals(userName)) {
    			a.setActive();
    			return true;
       		}
    	}
    	return false;
    }
    
    public boolean isPasswordCorrect(char[] pass) {
    	for(User a : UserListDAO.getInstance()) {
    		if(Arrays.equals(a.getPassword(), pass) && a.getActive()) {
    			return true;
    		}
    	}
		return false;
    }
    
    private void createRegisterPanel(JFrame frame, JPanel p) {
    	JPanel r = new JPanel(new BorderLayout(5,5));
    	JPanel rLabel = new JPanel(new GridLayout(4,1));
    	rLabel.add(new JLabel("Email to register: "));
    	rLabel.add(new JLabel("Name: "));
    	rLabel.add(new JLabel("Username: "));
    	rLabel.add(new JLabel("Password: "));
    	r.add(rLabel, BorderLayout.LINE_START);
    	JPanel rControl = new JPanel(new GridLayout(4,1));
    	JTextField textFields[] = new JTextField[3];
    	JPasswordField pass = new JPasswordField();
    	boolean correctRegistration = true;
    	
    	for(int i = 0; i < textFields.length; i++) {
    		rControl.add(textFields[i] = new JTextField());
    	}
    	rControl.add(pass);
    	r.add(rControl, BorderLayout.CENTER);
    	JOptionPane.showMessageDialog(frame, r, "Register a user",JOptionPane.OK_CANCEL_OPTION);
    	
    	for(int i = 0 ; i < textFields.length; i++) {
    		if(textFields[i].getText().isBlank()){
    			switch(i) {
    			case 0:
    				JOptionPane.showMessageDialog(this, "You need to enter an valid email.");
    				break;
    			case 1:
    				JOptionPane.showMessageDialog(this, "Please enter a name.");
    				break;
    			case 2:
    				JOptionPane.showMessageDialog(this, "Please enter a User name.");
    				break;
    			}		
    		}
    	}	
    	for(JTextField tf : textFields) {
    		if(tf.getText().isBlank()) {
    			correctRegistration = false;
    		}		
    	}
    	
    	if(correctRegistration)
    		new RegisterUser(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), pass.getPassword());
    }
}
