package controller;

import dao.UserListDAO;
import model.User;

public class RegisterUser {
	
	public RegisterUser(String email, String name, String userName, char[] password) {
		UserListDAO.getInstance().save(new User(email, name, userName, password));
		UserListDAO.getInstance().add(new User(email, name, userName, password));
	}
	
}
