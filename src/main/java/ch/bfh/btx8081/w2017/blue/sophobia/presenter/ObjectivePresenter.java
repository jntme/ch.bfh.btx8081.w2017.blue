package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	}

	@Override
	public void requestObjectiveWithPatientAndId(int pid, int oid) {
		Patient p = DB.getPatient(Integer.toString(pid));
		Objective objective = null;

		if (p != null) {
			List<Objective> objList = p.getObjectiveList().getObjectives();
			
			for(Objective obj : objList) {
				if (obj.getOid() == oid)
					objective = obj;
				break;
			}

			if (objective != null) {
				this.displayObjective(objective);
				return;
			}
		}
		
		view.patientAndObjectiveNotFound();

	}

}
