package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Manages all activity records for the specific activity
 * @author ziegm
 *
 */
@Entity
public class ActivityRecordList {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int arLId;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<ActivityRecord> activityRecords = new ArrayList<ActivityRecord>();
	
	
	/**
	 * Creates a new activity record in this list
	 * @param date
	 * @param success
	 */
	public void createActivityRecord(Date date, int success) {
		ActivityRecord activityRecord = new ActivityRecord(date, success);
		
		activityRecords.add(activityRecord);
	}
	
	
	/**
	 * @return the arId
	 */
	public int getArLId() {
		return arLId;
	}

	/**
	 * @param arId the arId to set
	 */
	public void setArLId(int arLId) {
		this.arLId = arLId;
	}

	/**
	 * @return the activityRecord
	 */
	public List<ActivityRecord> getActivityRecord() {
		return activityRecords;
	}

	/**
	 * @param activityRecord the activityRecord to set
	 */
	public void setActivityRecord(List<ActivityRecord> activityRecords) {
		this.activityRecords = activityRecords;
	}
	
	public void addActivityRecord(ActivityRecord activityRecord) {
		this.activityRecords.add(activityRecord);
	}
	
	public void removeActivityRecord(ActivityRecord activityRecord) {
		activityRecords.remove(activityRecord);
		activityRecord.delete();
	}
	
}
