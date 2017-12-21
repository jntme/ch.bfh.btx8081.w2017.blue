package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Delegates the data from the model to the view
 * 
 * @author ziegm1
 *
 */
public class ObjectivePresenter implements ObjectiveView.ObjectiveViewListener {

	private Objective model;
	private ObjectiveView view;

	public ObjectivePresenter(ObjectiveView view) {
		this.view = view;
		view.setListener(this);
	}

	public void displayObjective(Objective objective) {
		this.model = objective;

		view.setName(model.getName());
		view.setDescription(model.getDescription());
	}

    /**
     * Requests an Object with a specific id and oid and returns it
     *
     * @param pid
     * @param oid
     */
    @Override
    public void requestObjectiveWithPatientAndId(int pid, int oid) {
        Patient p = DB.getPatient(Integer.toString(pid));
        Objective objective = null;

        if (p != null) {
            List<Objective> objList = p.getObjectiveList().getObjectives();

            // look for objective
            for(Objective obj : objList) {
                if (obj.getOid() == oid) {
                    objective = obj;
                    break;
                }
            }

            // if found, display it
            if (objective != null) {
                this.displayObjective(objective);
                return; // do not continue, if found & set
            }
        }

        view.patientAndObjectiveNotFound();

    }

}
