package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Manages all objectives for a Patient
 * @author kybup1
 *
 */
@Entity
public class ObjectiveList {
	
	@Id
	private int olid;
	
	@OneToMany(mappedBy= "objList")
	private List<Objective> objectives;

	/**
	 * Creates a new Objective in this List
	 * @param name of the new objective
	 * @param description of the new objective
	 * @param  difficulty of the objective as Integer from 1 to 10
	 */
	public void createObj(String name, String description, int difficulty){
		Objective obj = new Objective();
		obj.setName(name);
		obj.setDescription(description);
		obj.setDifficulty(difficulty);
		obj.setComplete(false);
		
		objectives.add(obj);
	}

	public int getOlid() {
		return olid;
	}
	public void setOlid(int olid) {
		this.olid = olid;
	}
	public List<Objective> getObjectives() {
		return objectives;
	}
	public void setObjectives(List<Objective> objectives) {
		this.objectives = objectives;
	}
}
