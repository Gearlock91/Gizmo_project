package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.DbConnectionManager;
import model.TrackPoint;
import model.User;

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
	
	
	

//	@Override
//	public TrackPoint get(int id) {
//		return ;
//	}

	@Override
	public List<TrackPoint> getAll() {
		try {
			
			List<String> tp = new LinkedList<String>();
			
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT date, time, elapsed_time, longitude, latitude, altitude, distance, heart_rate, speed, cadance, a_id FROM TrackPoints");
			while (resultSet.next()) {
				
				tp.add(resultSet.getString(1));
				tp.add(resultSet.getString(2)); 
				tp.add(String.valueOf((resultSet.getInt(3))));
				tp.add(String.valueOf(resultSet.getDouble(4))); 
				tp.add(String.valueOf(resultSet.getDouble(5))); 
				tp.add(String.valueOf(resultSet.getDouble(6))); 
				tp.add(String.valueOf(resultSet.getDouble(7))); 
				tp.add(String.valueOf(resultSet.getDouble(8))); 
				tp.add(String.valueOf(resultSet.getDouble(9)));
				tp.add(String.valueOf(resultSet.getDouble(10)));
				tp.add(String.valueOf(resultSet.getInt(11))); 
				
				TrackPointDAO.getInstance().add(new TrackPoint(tp));
				
				tp.clear();
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return TrackPointDAO.getInstance();
	}

	
	@Override
	public TrackPoint save(TrackPoint t) {
		
	
	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rowCount = 0;
		//boolean saveSucess = false;
		try {
			//****This is just for checking the 'save' is a sucess. Count rows before save... ***
			
			resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(a_id) FROM Activities");
			resultSet.next();
			rowCount = resultSet.getInt(1);
			System.out.println(rowCount); // Debug print
			
			//*******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton.prepareStatement(
											  "INSERT INTO TrackPoints (a_id,date, time, elapsed_time, longitude, latitude, altitude, distance, heart_rate, speed, cadance) " +
											  "VALUES (?,?, ?, ?, ? , ?, ? , ?, ? , ? , ? );");
			
			resultSet.next();
			
			preparedStatement.setInt(1, rowCount++);

			preparedStatement.setString(2, t.getDate());
			preparedStatement.setString(3, t.getTime());
			preparedStatement.setInt   (4, t.getElapsedTime());
			preparedStatement.setDouble(5, t.getLong());
			preparedStatement.setDouble(6, t.getLat());
			preparedStatement.setDouble(7, t.getAlt());
			preparedStatement.setDouble(8, t.getDistance());
			preparedStatement.setDouble(9, t.getHart());
			preparedStatement.setDouble(10,t.getSpeed());
			preparedStatement.setDouble(11,t.getCadence());
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
