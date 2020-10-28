package data_handler;


import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TrackPoint;

class ImportDAOTest {

	List<TrackPoint> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test() {
		list = Import.getInstance().readAll("ClockData/activity_2016019890.csv");
		if(list != null) {
		//-------------
		for(TrackPoint a : list)
			System.out.println(a);
		//---------------------------
		}else {
			System.out.println("list is empty");
		}
	}

}
