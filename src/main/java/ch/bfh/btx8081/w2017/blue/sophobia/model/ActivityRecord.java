package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

/**
 * Model class for activity record
 * @author ziegm
 *
 */
@Entity
public class ActivityRecord implements Serializable {
	
	private static final long serialVersionUID = 3634336996507717961L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int arId;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private int success;
	private String description;
	
	public ActivityRecord() {
		
	}
	
	public ActivityRecord(Date date, int success, String description) {
		this.date = date;
		this.success = success;
		this.description = description;
	}
	
	public int getArId() {
		return arId;
	}
	
	public void setArId(int arId) {
		this.arId = arId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getSuccess() {
		return success;
	}
	
	public void setSuccess(int success) {
		this.success = success;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void delete() {
		EntityManager em = DB.getEntityManager();
		em.getTransaction().begin();
		em.remove(this);
		em.getTransaction().commit();
	}
}
