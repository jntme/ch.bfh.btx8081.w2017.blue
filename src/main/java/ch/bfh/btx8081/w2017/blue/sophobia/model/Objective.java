package ch.bfh.btx8081.w2017.blue.sophobia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Objective {
	
	@Id
	@GeneratedValue
	private int oid;
	private String name;
	private String description;
	private int difficulty;
	private boolean isComplete;
	@ManyToOne
	@JoinColumn(name="objlist")
	private ObjectiveList objList;
	
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
	public ObjectiveList getObjList() {
		return objList;
	}
	public void setObjList(ObjectiveList objList) {
		this.objList = objList;
	}
	
	
}
