package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;
import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.Breadcrumb;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityView;

/**
 * Presenter for Activity.
 *
 * @author gfels6, jntme
 */
public class ActivityPresenter implements ActivityView.ActivityViewListener, Serializable {

    private static final long serialVersionUID = -2999064597709591945L;

    private Activity model = null;
    private ActivityView view;
    private Patient patient = null;
    private Objective objective = null;
    private Breadcrumb breadcrumb = null;
    private boolean isNewActivity = false;

    public ActivityPresenter(ActivityView view, Breadcrumb bc) {
        this.view = view;
        this.breadcrumb = bc;
        view.setPresenter(this);
    }

    public void setActivity(Activity activity) {
        this.model = activity;

        view.setName(model.getName());
        view.setDescription(model.getDescription());
        view.setSubPresenter(model);

    }

    @Override
    public void requestActivity(int pid, int oid, int aid) {
        this.patient = DB.getObjectById(Integer.toString(pid), Patient.class, "pid");
        Objective objective = null;
        Activity activity = null;

        if (this.patient != null) {
            List<Objective> objList = this.patient.getObjectiveList().getObjectives();
            
            //set breadcrumb for patient
            breadcrumb.setPatLoc(this.patient);

            // look for objective
            for (Objective obj : objList) {
                if (obj.getOid() == oid) {
                    objective = obj;
                    break;
                }
            }
            
            //set breadcrumb for objective
            breadcrumb.setObjLoc(objective);

            List<Activity> actList = objective.getActList().getActivities();

            // look for activity
            for (Activity act : actList) {
                if (act.getAid() == aid) {
                    activity = act;
                    break;
                }
            }

            // if found, display it
            if (activity != null) {
            	
            	breadcrumb.setActLoc(activity);
                this.setActivity(activity);
                this.isNewActivity = false;
                return; // do not continue, if found & set
            }
        }

        view.patientAndObjectiveNotFound();
    }

    /**
     * Prepares the presenter for a new Activity. Saves the oid on this for later use.
     *
     * @param pid (patients id)
     * @param oid (objectives id)
     */
    @Override
    public void initNewActivity(int pid, int oid) {
        this.patient = DB.getObjectById(Integer.toString(pid), Patient.class, "pid");
        this.objective = DB.getObjectById(Integer.toString(oid), Objective.class, "oid");
        this.isNewActivity = true;

        ActivityRecordList activityRecordList = new ActivityRecordList();
        Activity activity = new Activity();
        activity.setActRecList(activityRecordList);

        setActivity(activity);
    }

    @Override
    public void setActivityName(String value) {
        this.model.setName(value);
    }

    @Override
    public void setActivityDescription(String value) {
        this.model.setDescription(value);
    }

    @Override
    public void save() {
        if (this.isNewActivity) {
            this.objective.getActList().addActivity(this.model);
            System.out.println("Persisting new Activity: " + this.model.toString());
            this.objective.persist();
            this.isNewActivity = false;

            this.view.addedActivity();
        } else {
            this.patient.persist();
            System.out.println("Save existing activity.");
        }
    }

    @Override
    public Patient getPatient() {
        return this.patient;
    }

    @Override
    public Objective getObjective() {
        return this.objective;
    }

    @Override
    public Activity getModel() {
        return this.model;
    }
}


