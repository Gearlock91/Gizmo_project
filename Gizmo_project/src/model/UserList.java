package model;

import java.util.LinkedList;

public class UserList extends LinkedList<User> {

	private static final long serialVersionUID = 1L;
	private static UserList instance;
	
	private UserList() {}
	
	public static UserList getInstance() {
		if(instance == null) {
			instance = new UserList();
		}
		return instance;
	}
}
