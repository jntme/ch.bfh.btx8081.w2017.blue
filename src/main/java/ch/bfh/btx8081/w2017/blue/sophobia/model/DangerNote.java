package ch.bfh.btx8081.w2017.blue.sophobia.model;

import javax.persistence.Entity;

/**
 * A special kind of note, which contains important information to sustain the patient safety.
 * The information has to be checked when the DangerNote is active (boolean)
 * @author kybup1
 *
 */
@Entity
public class DangerNote extends Note {
	
	boolean active;

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
