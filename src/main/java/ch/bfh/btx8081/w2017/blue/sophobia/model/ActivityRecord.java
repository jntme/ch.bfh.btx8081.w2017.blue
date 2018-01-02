package ch.bfh.btx8081.w2017.blue.sophobia.model;

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
public class ActivityRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int arId;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private int success;
	
	public ActivityRecord() {
		
	}
	
	public ActivityRecord(Date date, int success) {
		this.date = date;
		this.success = success;
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
	
	public void delete() {
		EntityManager em = DB.getEntityManager();
		em.getTransaction().begin();
		em.remove(this);
		em.getTransaction().commit();
	}
	
}
