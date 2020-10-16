package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dao.IDao;
import db.DbConnectionManager;

public class UserList extends LinkedList<User> implements IDao<User>{

	private static final long serialVersionUID = 1L;
	private static UserList instance;
	static DbConnectionManager dbConManagerSingleton = null;
	
	private UserList() {}
	
	public static UserList getInstance() {
		if(instance == null) {
			instance = new UserList();
			dbConManagerSingleton = DbConnectionManager.getInstance();
		}
		return instance;
	}

	@Override
	public List<User> getAll() {

		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT email, name, userName,password FROM Users");
			while (resultSet.next()) {
				UserList.getInstance().add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4).toCharArray()));
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return UserList.getInstance().getAll();
	}

	@Override
	public User save(User t) {
		
		
	}

	@Override
	public void update(User t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}
}
