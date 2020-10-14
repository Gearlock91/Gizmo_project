package model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrackPointTest {

	private List<String> dataPoint;
	private Activity activity;
	private Scanner s;
	
	public List<String> collectPoints(String s){
		dataPoint.clear();
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
	
	@BeforeEach
	void setUp() throws Exception {
		
		dataPoint = new LinkedList<String>();
		activity = new Activity("Test run");
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test() {
		try {
			s = new Scanner(new BufferedReader(new FileReader("trackData.txt")));
	
			while(s.hasNext()) {
				activity.addPoint(new TrackPoint(collectPoints(s.next())));
			}
			
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (s != null) {
	            s.close();
	        }
	    
		}
		
		for (int i = 0; i < activity.size(); i++) {
			System.out.println(activity.getPoint(i).toString());
		}
		
		
		assertEquals("11:50:16", activity.getPoint(5).getTime());
		assertEquals(160.0, activity.getPoint(18).getCadence());
	}

}
