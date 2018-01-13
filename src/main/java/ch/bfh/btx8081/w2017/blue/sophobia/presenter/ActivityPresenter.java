package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.model.*;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityView;

public class ActivityPresenter implements ActivityView.ActivityViewListener {

    private Activity model = null;
    private ActivityView view;
    private Patient patient = null;
    private Objective objective = null;
    private boolean isNewActivity = false;
    private ActivityRecordList actRecList;

    public ActivityPresenter(ActivityView view) {
        this.view = view;
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
        this.patient = DB.getPatient(Integer.toString(pid));
        Objective objective = null;
        Activity activity = null;

        if (this.patient != null) {
            List<Objective> objList = this.patient.getObjectiveList().getObjectives();

            // look for objective
            for (Objective obj : objList) {
                if (obj.getOid() == oid) {
                    objective = obj;
                    break;
                }
            }

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
        this.patient = DB.getPatient(Integer.toString(pid));
        this.objective = DB.<Objective>getObjectById(Integer.toString(oid), "Objective");
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


