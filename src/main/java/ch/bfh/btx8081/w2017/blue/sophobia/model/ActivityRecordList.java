package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
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
 *
 * @author ziegm
 */
@Entity
public class ActivityRecordList implements Serializable {

    private static final long serialVersionUID = -415499546716909188L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int arLId;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ActivityRecord> activityRecords = new ArrayList<ActivityRecord>();

    /**
     * Creates a new activity record in this list
     *
     * @param date
     * @param success
     * @param description
     * @return
     */
    public ActivityRecord createActivityRecord(Date date, int success, String description) {
        ActivityRecord activityRecord = new ActivityRecord(date, success, description);

        activityRecords.add(activityRecord);
        return activityRecord;
    }

    public int getArLId() {
        return arLId;
    }

    public void setArLId(int arLId) {
        this.arLId = arLId;
    }

    public List<ActivityRecord> getActivityRecord() {
        return activityRecords;
    }

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
