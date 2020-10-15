package view;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class LoginScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	

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
        controls.add(register);
        p.add(controls, BorderLayout.CENTER);

        logIn(frame,p,username,password);
        
    }
    
    public void logIn(JFrame frame, JPanel p, JTextField userName, JPasswordField password) {
    	int opt = JOptionPane.showConfirmDialog(frame, p, "Log In", JOptionPane.OK_CANCEL_OPTION);
    	     	  
          if(isPasswordCorrect(password.getPassword()) && isUserNameCorrect(userName.getText())){
        	JOptionPane.showMessageDialog(frame, "Success!");
        	
          }
          else if(opt == JOptionPane.CANCEL_OPTION)
        	  System.exit(0);
          else
          	logIn(frame,p,userName,password);
         
    }
  
    
    public boolean isUserNameCorrect(String userName) {
    	String correctUserName = "Andreas";
    	
    	if(userName.length() != correctUserName.length())
    		return false;
    	
    	return correctUserName.equals(userName);
    }
    
    public boolean isPasswordCorrect(char[] pass) {
    	
    	char [] correctPass = {'n','m','o'};
    	
    	if(pass.length != correctPass.length)
    		return false;
    	else
    		return Arrays.equals(correctPass, pass);
    }
	
}
