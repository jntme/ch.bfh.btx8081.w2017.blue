package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityView;

public class ActivityPresenter implements ActivityView.ActivityViewListener{
	
    private Activity model = null;
    private ActivityView view;
    private Patient patient = null;
    private boolean isNewActivity = false;
	
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
		
	}


