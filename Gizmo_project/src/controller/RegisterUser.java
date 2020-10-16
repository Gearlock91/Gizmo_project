package controller;

import model.User;
import model.UserListDAO;

public class RegisterUser {
	
	public RegisterUser(String email, String name, String userName, char[] password) {
		UserListDAO.getInstance().save(new User(email, name, userName, password));
	}
	
}
