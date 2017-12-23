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

	private Objective model = null;
	private ObjectiveView view = null;
	private Patient patient = null;

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
     * @param pid: the patient id
     * @param oid: the objecitve id
     */
    @Override
    public void requestObjectiveWithPatientAndId(int pid, int oid) {
        this.patient = DB.getPatient(Integer.toString(pid));
        Objective objective = null;

        if (this.patient != null) {
            List<Objective> objList = this.patient.getObjectiveList().getObjectives();

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

    /**
     * Prepares the presenter for a new Objective. saves the pid on this for later use.
     *
     * @param pid: the corresponding patient id
     */
    @Override
    public void initNewObjective(int pid) {
        this.patient = DB.getPatient(Integer.toString(pid));

        displayObjective(new Objective());
    }
}
