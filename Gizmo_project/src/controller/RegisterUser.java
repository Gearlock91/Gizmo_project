package controller;

import model.User;
import model.UserList;

public class RegisterUser {
	
	public RegisterUser(String email, String name, String userName, char[] password) {
		UserList.getInstance().add(new User(email, name, userName, password));
	}
	
}
