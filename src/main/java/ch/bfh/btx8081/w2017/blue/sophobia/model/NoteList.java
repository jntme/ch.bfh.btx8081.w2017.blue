package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.rmi.CORBA.UtilDelegate;

/**
 * Manages all notes for a patient
 * @author kybup1
 *
 */
@Entity
public class NoteList {
	
	@Id
	@GeneratedValue
	private int nlid;
	
	@OneToMany(mappedBy= "noteList")
	private List<Note> notes = new ArrayList<Note>();
	
	/**
	 * Creates a new note.
	 * Needs the title and the content of the note.
	 * Depending on the value of the boolean a different kind of note will be created
	 * true = DangerNote, false=Note
	 * Furthermore the Systemtime is captured and saved 
	 * @param title
	 * @param content
	 * @param danger
	 */
	public void createNote(String title, String content, boolean danger){
		
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		
		if(danger){
			DangerNote note = new DangerNote();
			note.setTitle(title);
			note.setContent(content);
			note.setDate(date);
			note.setActive(true);
			notes.add(note);
		} else {
			Note note = new Note();
			note.setTitle(title);
			note.setContent(content);
			note.setDate(date);
			notes.add(note);
		}
	}
	
	/**
	 * Chechs if a given note is a DangerNote and returns a boolean
	 * @param note
	 * @return boolean, true = is a DangerNote
	 */
	public boolean isDangerNote(Note note){
		if (DangerNote.class.isInstance(note))
			return true;
		else
			return false;
	}

	public int getNlid() {
		return nlid;
	}

	public void setNlid(int nlid) {
		this.nlid = nlid;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	
}
