package data_handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dao.TrackPointDAO;
import model.TrackPoint;

public class Import {

    private static Import instance;

    private Import(){}

    public static Import getInstance(){
        if(instance == null){
            instance = new Import();
        }
        return instance;
    }
    // -------- END SINGLETON PART --------
    // -------- BEGIN import       --------
    private List<TrackPoint> trackPointList;
    private List<String>     dataPoint;
    private Scanner s;

    public List<String> collectPoints(String s){
		dataPoint = new LinkedList<String>();
		char character;
		String line = "";
		String holdLine = "";
		
		line = s;
		for(int i = 0; i < line.length() ; i++) {
			character = line.charAt(i);
			if(character == ';' ) {
				dataPoint.add(holdLine);
				holdLine = "";
			}
			else if(character == ',') {
				character = '.';
				holdLine += character;
			}
			else {
				holdLine += character;
			}
		}
		dataPoint.add(holdLine);
		return dataPoint;
	}
       
	public List<TrackPoint> readAll(String fileName){
       trackPointList = new LinkedList<TrackPoint>();
    	try {
			s = new Scanner(new BufferedReader(new FileReader(fileName)));
			
			boolean FirstLine  = true;
			
			while(s.hasNext()) {
				if(FirstLine == true) {
					
					FirstLine = false; 
					s.next();
					}	
				else {	
					trackPointList.add(new TrackPoint(0,collectPoints(s.next())));
				}			
			}
			
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (s != null) {
	            s.close();
	        }
	    
		}
        return trackPointList;    
    }
}
