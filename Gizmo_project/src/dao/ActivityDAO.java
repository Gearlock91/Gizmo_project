package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sound.midi.Track;

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
		try {
			TrackPointDAO.getInstance().getAll();
			User active = null;
			for(User a : UserListDAO.getInstance()) {
				if(a.getActive())
					active = a;
			}
			
			List <TrackPoint> listTp = new LinkedList<TrackPoint>();

			
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT a_id, u_id, activity_name FROM Activities");
			
			while(resultSet.next()) {
				if(active.getUid() == resultSet.getInt(2)) {
					for(int i = 0; i < TrackPointDAO.getInstance().size();i++) {
						if(TrackPointDAO.getInstance().get(i).getAID() == resultSet.getInt(1)) {
							listTp.add(TrackPointDAO.getInstance().get(i));
						}
					}
					ActivityDAO.getInstance().add(new Activity(resultSet.getString(3),listTp));
					listTp.clear();
				}
			}
			
			
			
//			while (resultSet.next()) {
//				
//				System.out.println(active.getUid());
//				System.out.println(resultSet.getInt(2));
//					
//				if(active.getUid() != resultSet.getInt(2)) {}
//				
//				else {
//				
//					for(int i = 0; j < TrackPointDAO.getInstance().size();i++) {
//						if((j + 1) == TrackPointDAO.getInstance().size()) {
//							ActivityDAO.getInstance().add(new Activity(resultSet.getString(3),listTp));
//							listTp.clear();
//							break;
//						}
//						else {
//							if((TrackPointDAO.getInstance().get(j).getAID() == resultSet.getInt(1))) {
//								listTp.add(TrackPointDAO.getInstance().get(j));
//								j++;
//							}
//							else {
//								ActivityDAO.getInstance().add(new Activity(resultSet.getString(3),listTp));
//								listTp.clear();
//								//j = j - 1;
//								break;
//							}
//						}
//					}		
//				}
//			}
			
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return ActivityDAO.getInstance();
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
			preparedStatement.setString(2, t.getName());
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
