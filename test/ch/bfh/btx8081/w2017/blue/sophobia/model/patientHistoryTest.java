import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.model.PatientHistory;
/**
 * @Odaoj1
 */

public class patientHistoryTest {

	
		@Test
		public void tesPatientHistory() {
			PatientHistory ph1 = PatientHistory.getInstance();
		    System.out.println("patientHistory");
		   List<E> hl = new List("this week","last week");
		   assertTrue("this is patient History", ph1.getPatientHistory(hl.get(0)));
		    
		}

		/**
		 * Test of add method, of class Testcases.
		 */
		@Test
		public void testMyArray() {
			PatientHistory  ph = PatientHistory.getInstance();
			
			ArrayList<E> al= new ArrayList<>(Arrays.asList("HistoryOne,"HistoryTwo"));
			assertFalse( " this is last week's history", al.toString(ph.getPatientHistory()));
		  
		

		
		
	}

}
