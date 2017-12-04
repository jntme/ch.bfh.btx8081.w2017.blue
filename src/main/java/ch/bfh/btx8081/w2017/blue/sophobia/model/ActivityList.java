package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ActivityList {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int alid;
	
	@OneToMany(mappedBy= "actList", cascade = CascadeType.ALL)
	private List<Activity> activities = new ArrayList<Activity>();
	
	public void createAct(String name, String description){
		Activity act = new Activity(name, description);
		
		activities.add(act);	
	}
	
	public int getAlid() {
		return alid;
	}
	public void setAlid(int alid) {
		this.alid = alid;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}