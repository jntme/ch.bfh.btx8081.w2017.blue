package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

/**
 * Defines the behavior of the Objective
 * @author ziegm1
 *
 */
public interface ObjectiveView {
	
	void setDescription(String description);
	void setDifficulty(int difficulty);
	void setIscomplete(String isComplete);
	void setSubPresenter(Objective model);
	void setName(String name);

    void addedObjective();

    interface ObjectiveViewListener {
		void requestObjectiveWithPatientAndId(int pid, int oid);
		void initNewObjective(int pid);

		void setObjectiveName(String value);
		void setObjectiveDescription(String value);
		void setObjectiveDifficulty(int value);

        void save();

        Objective getModel();

        Patient getPatient();
    }
	
	void setPresenter(ObjectiveViewListener presenter);
	void patientAndObjectiveNotFound();
	void clearView();
	void sendToActivityList(Patient patient, Objective model);
}
