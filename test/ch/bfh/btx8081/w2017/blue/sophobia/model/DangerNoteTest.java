package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

public class DangerNoteTest {

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
		Date today = new Date(Calendar.getInstance().getTime().getTime());
		DangerNote note = new DangerNote("Test", "Aaaahh even more danger");
		
		assertTrue("Date of the created note is today", note.getDate().compareTo(today)==0);	
	}

}
