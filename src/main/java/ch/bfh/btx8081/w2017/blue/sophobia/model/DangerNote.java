package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * A special kind of note, which contains important information to sustain the patient safety.
 * The information has to be checked when the DangerNote is active (boolean)
 * @author kybup1
 *
 */
@Entity
@DiscriminatorValue("d")
public class DangerNote extends Note implements Serializable  {
	
	private static final long serialVersionUID = 658270098333780763L;
	
	boolean active;
	
	public DangerNote(){
		
	}
	public DangerNote(String title, String content){
		super(title, content);
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		super.setDate(date);
		this.active = true;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
