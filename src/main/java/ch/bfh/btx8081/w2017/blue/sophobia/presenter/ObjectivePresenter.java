package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Delegates the data from the model to the view
 * @author ziegm1
 *
 */
public class ObjectivePresenter {

	private Objective model;
	private ObjectiveView view;
	
	public ObjectivePresenter(ObjectiveView view) {
		this.view = view;
		this.model = initializePatient();
		view.setName(model.getName());
		view.setPresenter(this);
	}
	
	private Objective initializePatient() {
		EntityManager em = DB.getEntityManager();
		
		Query q2 = em.createQuery("select m from Objective m");
		
		List<Objective> objectiveList = q2.getResultList();
		Objective objectiveZero = null;
		if (!objectiveList.isEmpty()) {
			return objectiveList.get(0);
		}
		
		return null;
	}

}
