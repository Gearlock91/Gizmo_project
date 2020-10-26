package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbConnectionManager;
import model.Activity;
import model.User;

public class BicyclingDAO implements IDao<Activity>{
	
	private DbConnectionManager dbConManagerSingleton = DbConnectionManager.getInstance();
	
	@Override
	public List<Activity> getAll() {
		return null;
	}

	@Override
	public Activity save(Activity t) {
		
		User active = null;
		
		for(User a : UserListDAO.getInstance())
			if(a.getActive())
				active = a;
		
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
											  "INSERT INTO bicycling (min_heart, max_heart, avg_heart, total_distance, total_time, u_id) " +
											  "VALUES (?, ?, ?, ?, ? ,?);");
			preparedStatement.setDouble(1, t.getMinHeart());
			preparedStatement.setDouble(2, t.getMaxHeartActivity());
			preparedStatement.setDouble(3, t.getAvgHeartRate());
			preparedStatement.setDouble(4, t.getDistance());
			preparedStatement.setString(5, t.getTotalTime());
			preparedStatement.setInt   (6, active.getUid());
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			//resultSet.next();
			//return new User(t.getEmail(), t.getName(), t.getUserName(), t.getPassword());
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
	public void update(Activity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity t) {
		// TODO Auto-generated method stub
		
	}

}
