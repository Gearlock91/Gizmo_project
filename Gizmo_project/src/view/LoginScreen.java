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

import controller.RegisterUser;
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
    
    	
          if(isPasswordCorrect(password.getPassword()) && isUserNameCorrect(userName.getText())){
        	JOptionPane.showMessageDialog(frame, "Success!");
        	succecfullLogin = true;
          }
          else if(opt == JOptionPane.CANCEL_OPTION)
        	  System.exit(0);
          else
          	logIn(frame,p,userName,password);
         
    }
    
    public boolean getLogIn() {
    	return succecfullLogin;
    }
  
    
    public boolean isUserNameCorrect(String userName) {
    	
    	String correctUserName = "";
    	
    	for(User a : UserListDAO.getInstance()) {
    		if(a.getUserName().equals(userName)){
    			correctUserName = a.getUserName();
    			a.setActive();
    		}
    			
    	} 	
    	
    	if(userName.length() != correctUserName.length())
    		return false;
    	
    	return correctUserName.equals(userName);
    }
    
    public boolean isPasswordCorrect(char[] pass) {
    	
    	char [] correctPass = null;
    	
    	for(User a : UserListDAO.getInstance()) {
    		if(Arrays.equals(a.getPassword(), pass))
    			correctPass = a.getPassword();
    	}
    	
    	if(pass.length != correctPass.length)
    		return false;
    	else
    		return Arrays.equals(correctPass, pass);
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
    	JTextField e = new JTextField();
    	JTextField n = new JTextField();
    	JTextField u = new JTextField();
    	JPasswordField pass = new JPasswordField();
    	
    	rControl.add(e);
    	rControl.add(n);
    	rControl.add(u);
    	rControl.add(pass);
    	r.add(rControl, BorderLayout.CENTER);
    	JOptionPane.showMessageDialog(frame, r, "Register a user",JOptionPane.OK_CANCEL_OPTION);
    	
    	new RegisterUser(e.getText(), n.getText(), u.getText(), pass.getPassword());
    }
}
