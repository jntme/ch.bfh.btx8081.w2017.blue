package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

public class DangerNoteTest {
	
	/**
	 * @author kybup1
	 */
	
	@Test
	public void checkIsDanger(){
		DangerNote note = new DangerNote("Test", "Uuuh Dangerous");
		
		assertTrue("Check if Dangernote is active on creation", note.isActive());
	}
	
	@Test
	public void changeDangerNoteToInactive(){
		DangerNote note = new DangerNote("Test", "Uuuh danger");
		note.setActive(false);
		
		assertFalse("Changes the state of the Dangernote to inactive and checks it", note.isActive());
	}
	
	@Test
	public void checkTimeStamp(){
		Calendar cal1 = Calendar.getInstance();
		DangerNote dNote = new DangerNote("Test", "Uuuhh even more danger!");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(dNote.getDate().getTime());		
		
		assertTrue("Date of the created note is today", cal1.get(Calendar.DATE)==cal2.get(Calendar.DATE));	
	}

}
