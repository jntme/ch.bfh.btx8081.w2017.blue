package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

public interface ActivityView {
	
	void setDescription(String description);
	void setIscomplete(String isComplete);
	void setSubPresenter(Activity model);
	void setName(String name);

	void addedActivity();

	void clearView();
	
	void setPresenter(ActivityViewListener presenter);
	 interface ActivityViewListener {
		 void requestActivity(int pid, int oid, int aid);
         void initNewActivity(int pid, int oid);

         void setActivityName(String value);
         void setActivityDescription(String value);

         void save();

		 Patient getPatient();
		 Objective getObjective();
		 Activity getModel();
     }
	void patientAndObjectiveNotFound();

	void sendToActivityRecordList(Patient patient, Objective model, Activity activity);
}
