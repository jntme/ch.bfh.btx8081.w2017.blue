package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Represents notes with a title, a content and the date when their created/modified.
 * @author kybup1
 *
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="note_type")
public class Note {
	
	@Id
	@GeneratedValue
	private int nid;
	private String title;
	private String content;
	private Date date;
	
	public Note(){
		
	}
	/**
	 * Constructor that fills the note object with all attributes.
	 * Takes the current system date as date attribute.
	 * @param title
	 * @param content
	 */
	public Note(String title, String content){
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		this.title = title;
		this.content = content;
		this.date = date;
	}
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
