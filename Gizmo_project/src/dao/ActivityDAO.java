package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.DbConnectionManager;
import model.Activity;
import model.TrackPoint;
import model.User;

public class ActivityDAO extends LinkedList<Activity> implements IDao<Activity>{

	private static final long serialVersionUID = 1L;
	
	static DbConnectionManager dbConManagerSingleton = null;
	private static ActivityDAO instance;
	
	private ActivityDAO() {}
	
	public static ActivityDAO getInstance() {
			if(instance==null) {
				instance = new ActivityDAO();
				dbConManagerSingleton = DbConnectionManager.getInstance();
			}
				
		return instance;
	}
	
//--------- SINGELTON -----------------
	
	
	

//	@Override
//	public Activity get(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Activity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Activity save(Activity t) {
		
		
		User a = null;
		
		for(User b : UserListDAO.getInstance()) {
			if(b.getActive())
				a = b;
		}
		
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
											  "INSERT INTO Activities (u_id, activity_name) " +
											  "VALUES (?, ?);");
			
			preparedStatement.setInt(1, a.getUid());
			preparedStatement.setString(2, "Test");
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			
			//resultSet.next();
			return new Activity();
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
		
		return new Activity();
	}

	@Override
	public void update(Activity t, String[] param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity t) {
		// TODO Auto-generated method stub
		
	}

}
