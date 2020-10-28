package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.DbConnectionManager;
import model.User;

public class UserListDAO extends LinkedList<User> implements IDao<User>{

	private static final long serialVersionUID = 1L;
	private static UserListDAO instance;
	static DbConnectionManager dbConManagerSingleton = null;
	
	private UserListDAO() {}
	
	public static UserListDAO getInstance() {
		if(instance == null) {
			instance = new UserListDAO();
			dbConManagerSingleton = DbConnectionManager.getInstance();
		}
		return instance;
	}

	@Override
	public List<User> getAll() {
		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT email, name, user_name,password, u_id, Weight, age, height FROM Users");
			while (resultSet.next()) {
				UserListDAO.getInstance().add(new User(resultSet.getString(1), 
													   resultSet.getString(2), 
													   resultSet.getString(3), 
													   resultSet.getString(4).toCharArray(), 
													   resultSet.getInt(5),
													   resultSet.getDouble(6),
													   resultSet.getInt(7),
													   resultSet.getDouble(8)));
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return UserListDAO.getInstance();
	}

	@Override
	public User save(User t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//int rowCount = 0;
		//boolean saveSucess = false;
		try {
			//****This is just for checking the 'save' is a sucess. Count rows before save... ***
		//	resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM students");
		//	resultSet.next();
		//	rowCount = resultSet.getInt(1);
			//System.out.println(rowCount); // Debug print
			
			//*******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton.prepareStatement(
											  "INSERT INTO Users (email, name, user_name, password) " +
											  "VALUES (?, ?, ?, ?);");
			preparedStatement.setString(1, t.getEmail());
			preparedStatement.setString(2, t.getName());
			preparedStatement.setString(3, t.getUserName());
			preparedStatement.setString(4, new String(t.getPassword()));
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			//resultSet.next();
			return new User(t.getEmail(), t.getName(), t.getUserName(), t.getPassword());
			// ********************************************************************
			
			// **** Check nbr of rows after 'save'. Compare with previous row count *****
//			resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM students");
//			resultSet.next();
//			int newRowCount = resultSet.getInt(1);
//			if( newRowCount == (rowCount + 1)) // Check if table is one more row after 'save'
//				saveSucess = true;
//			System.out.format("Previous row count: %d    Current row count: %d", rowCount, newRowCount);
		}
		catch ( SQLException e) {
			e.printStackTrace();
		}
		
		return new User("No Name", "null", "null",null);
	}
		
	@Override
	public void update(User t) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		try {
		
			preparedStatement = dbConManagerSingleton.prepareStatement(
											  "UPDATE Users " +
											  "  SET weight   = ?,"
											  + "age 	  = ?," +
											  "height = ? " +
											  "  WHERE u_id   = ?");
			preparedStatement.setDouble(1, t.getWeight());
			preparedStatement.setInt(2, t.getAge());
			preparedStatement.setDouble(3, t.getHeight());
			preparedStatement.setInt(4, t.getUid());
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
		}
		catch ( SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}
	
}
