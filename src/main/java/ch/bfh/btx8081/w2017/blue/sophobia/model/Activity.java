package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

/**
 * The class Activity
 *
 * @author petim1
 */
@Entity
public class Activity implements Serializable {

    private static final long serialVersionUID = 3685403017342004247L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int aid;

    private String name = "";
    private String description = "";
    private boolean isComplete = false;

    @OneToOne(cascade = CascadeType.ALL)
    private ActivityRecordList activityRecordList = new ActivityRecordList();

    public Activity() {

    }

    public Activity(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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

    public boolean getComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }


    public ActivityRecordList getActRecList() {
        return activityRecordList;
    }

    public void setActRecList(ActivityRecordList activityRecordList) {
        this.activityRecordList = activityRecordList;
    }

    public void delete() {
        EntityManager em = DB.getEntityManager();
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
    }

}
