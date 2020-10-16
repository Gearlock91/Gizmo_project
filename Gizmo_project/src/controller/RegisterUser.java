package controller;

import model.User;
import model.UserList;

public class RegisterUser {
	
	public RegisterUser(String email, String name, String userName, String password) {
		UserList.getInstance().add(new User(email, name, userName, password));
		System.out.println("User created!");
	}
	
}
