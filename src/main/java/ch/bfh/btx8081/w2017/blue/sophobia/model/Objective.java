package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

@Entity
public class Objective implements Serializable {

	private static final long serialVersionUID = 8935434372674375377L;

	@Id
	@GeneratedValue
	private int oid;

	private String name  = "";
	private String description = "";

	private int difficulty = 5;
	private boolean isComplete = false;

	
	@OneToOne(cascade = CascadeType.ALL)
	private  ActivityList actList;
	
	public Objective(){
		
	}
	public Objective(String name, String description, int difficulty){
		this.name = name;
		this.description = description;
		this.difficulty = difficulty;
	}
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	
	public ActivityList getActList(){
		return actList;
	}	
	public void setActList(ActivityList actList){
		this.actList = actList;
	}

	public void persist() {
		EntityManager em = DB.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(this);
		trans.commit();
	}

    public void delete() {
        EntityManager em = DB.getEntityManager();
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
    }
}
