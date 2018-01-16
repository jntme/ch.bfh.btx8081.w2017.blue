package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordView;

/**
 * This class controls a given implementation of the ActivityRecordView Interface.
 *
 * @author kybup1
 */
public class ActivityRecordPresenter implements ActivityRecordView.ActivityRecordViewListener {

    private ActivityRecordView view;
    private Patient pat;
    private Objective obj;
    private Activity act;
    private ActivityRecord actRec;

    private boolean newActRec = false;


    public ActivityRecordPresenter(ActivityRecordView view) {
        this.view = view;
        view.addListener(this);
    }

    /**
     * Fills the view with data of an existing activity record.
     */
    private void loadActRec() {
        view.setDate(actRec.getDate());
        view.setDescription(actRec.getDescription());
        view.setSuccess(actRec.getSuccess());
    }

    /**
     * Is called when the save Button is pressed. Different actions are perfomred depending if the activity record is nwely created
     * or an existing one is been modified.
     */
    @Override
    public void save() {

        if (newActRec == false) {
            actRec.setDate(view.getDate());
            actRec.setDescription(view.getDescription());
            actRec.setSuccess(view.getSuccess());
            pat.persist();
        } else {
            actRec = act.getActRecList().createActivityRecord(view.getDate(), view.getSuccess(), view.getDescription());
            pat.persist();
            newActRec = false;
            view.clearView();
            view.changeToExistingActRec(actRec.getArId());
        }
    }

    @Override
    public void resolveIds(int pid, int oid, int aid, int arid) {
        pat = DB.getObjectById(Integer.toString(pid), Patient.class, "pid");

        if (this.pat != null) {
            List<Objective> objList = this.pat.getObjectiveList().getObjectives();

            // look for objective
            for (Objective obj : objList) {
                if (obj.getOid() == oid) {
                    this.obj = obj;
                    break;
                }
            }

            List<Activity> actList = obj.getActList().getActivities();

            // look for activity
            for (Activity act : actList) {
                if (act.getAid() == aid) {
                    this.act = act;
                    break;
                }
            }

            List<ActivityRecord> actRecList = act.getActRecList().getActivityRecord();

            if (arid == -1) {
                actRec = new ActivityRecord();
                newActRec = true;
                view.clearView();
                view.setNewActivityRecord();
            } else {
                for (ActivityRecord actRec : actRecList) {
                    if (actRec.getArId() == arid) {
                        this.actRec = actRec;
                        break;
                    }
                }
                view.clearView();
                loadActRec();
            }
        }

    }

}
