package model;


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
	private Scanner s;
	
	@BeforeEach
	void setUp() throws Exception {
		
		dataPoint = new LinkedList<String>();	
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test() {
		try {
			s = new Scanner(new BufferedReader(new FileReader("trackData.txt")));
			
			
			char character;
			String line = "";
			String holdLine = "";
			
			
				line = s.next();
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
				
	
			System.out.println(dataPoint.toString());
	//		System.out.println(line + " Nothing");
			
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (s != null) {
	            s.close();
	        }
	    
		}
		
		
		TrackPoint point = new TrackPoint(dataPoint);
		
		System.out.println(point.toString());
	}

}
