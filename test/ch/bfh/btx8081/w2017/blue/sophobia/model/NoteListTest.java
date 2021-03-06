package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

public class NoteListTest {
	
	/**
	 * @author kybup1
	 */
	
	@Test
	public void isDangerNote(){
		Note note = new DangerNote("Test", "New Test note");
		NoteList notelist = new NoteList();
		
		assertTrue("Note is recognized as Danger Note", notelist.isDangerNote(note));
	}
	
	@Test
	public void isDangerNote2(){
		Note note = new Note("Test", "New Test note");
		NoteList notelist = new NoteList();
		
		assertFalse("Note is recognized as normal Note", notelist.isDangerNote(note));
	}
	
	@Test
	public void createDangerNote(){
		NoteList list = new NoteList();
		Note dNote = list.createNote("Dangerous Test", "critical blablabla", true);
		
		assertTrue("Newly created Note is an instance of DangerNote", DangerNote.class.isInstance(dNote));
	}

	@Test
	public void checkTimestamp() {
		Calendar cal1 = Calendar.getInstance();
		NoteList list = new NoteList();
		Note note = list.createNote("Test", "Blablabla", false);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(note.getDate().getTime());		
		
		assertTrue("Date of the created note is today", cal1.get(Calendar.DATE)==cal2.get(Calendar.DATE));		
	}
	
}
