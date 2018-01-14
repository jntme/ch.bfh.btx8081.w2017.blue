package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Manages all objectives for a Patient
 * @author kybup1
 *
 */
@Entity
public class ObjectiveList implements Serializable {
	
	private static final long serialVersionUID = 5378381756557378278L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int olid;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Objective> objectives = new ArrayList<>();

	/**
	 * Creates a new Objective in this List
	 * @param name of the new objective
	 * @param description of the new objective
	 * @param  difficulty of the objective as Integer from 1 to 10
	 */
	public void createObj(String name, String description, int difficulty){
		Objective obj = new Objective(name, description, difficulty);
		
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

	public void addObjective(Objective objective) {
		this.objectives.add(objective);
	}

    public void removeObjective(Objective objective) {
		objectives.remove(objective);
		objective.delete();
    }
}
