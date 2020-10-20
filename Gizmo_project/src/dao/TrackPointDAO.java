package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.DbConnectionManager;
import model.TrackPoint;

public class TrackPointDAO extends LinkedList<TrackPoint> implements IDao<TrackPoint>{

	private static final long serialVersionUID = 1L;
	
	static DbConnectionManager dbConManagerSingleton = null;
	private static TrackPointDAO instance;
	
	private TrackPointDAO() {}
	
	public static TrackPointDAO getInstance() {
			if(instance==null) {
				instance = new TrackPointDAO();
				dbConManagerSingleton = DbConnectionManager.getInstance();
			}
				
		return instance;
	}
	
//--------- SINGELTON -----------------
	
	
	

	@Override
	public TrackPoint get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrackPoint> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public TrackPoint save(TrackPoint t) {
		
		
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
											  "INSERT INTO TrackPoints (date, time, elapsed_time, longitude, latitude, altitude, distance, heart_rate, speed, cadance) " +
											  "VALUES (?, ?, ?, ? , ?, ? , ?, ? , ? , ? );");
			preparedStatement.setString(1, t.getDate());
			preparedStatement.setString(2, t.getTime());
			preparedStatement.setInt   (3, t.getElapsedTime());
			preparedStatement.setDouble(4, t.getLong());
			preparedStatement.setDouble(5, t.getLat());
			preparedStatement.setDouble(6, t.getAlt());
			preparedStatement.setDouble(7, t.getDistance());
			preparedStatement.setDouble(8, t.getHart());
			preparedStatement.setDouble(9,t.getSpeed());
			preparedStatement.setDouble(10,t.getCadence());
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			//resultSet.next();
			return new TrackPoint(t);
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
		TrackPoint tp = null;
		return new TrackPoint(tp);
	}

	@Override
	public void update(TrackPoint t, String[] param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TrackPoint t) {
		// TODO Auto-generated method stub
		
	}

}
