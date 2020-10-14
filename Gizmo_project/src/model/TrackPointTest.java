package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrackPointTest {

	private String[] array;
	private Scanner s;
	
	@BeforeEach
	void setUp() throws Exception {
		
		array = new String[10];	
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test() {
		try {
		s = new Scanner(new BufferedReader(new FileReader("trackData.txt")));

            System.out.println(s.next());
        
    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
        if (s != null) {
            s.close();
        }
    
	}
}

}
