package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represents notes with a title, a content and the date when their created/modified.
 * @author kybup1
 *
 */

@Entity
public class Note {
	
	@Id
	@GeneratedValue
	private int nid;
	private String title;
	private String content;
	private Date date;
	@ManyToOne
	private int noteList;
	
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
	public int getNoteList() {
		return noteList;
	}
	public void setNoteList(int noteList) {
		this.noteList = noteList;
	}
}
