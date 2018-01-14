package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.SelectPatientView;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.SelectPatientView.SelectPatientClickListener;

public class SelectPatientPresenter implements SelectPatientClickListener, Serializable {

	private static final long serialVersionUID = 4423698583918640850L;

	public SelectPatientPresenter(SelectPatientView view) {
		List<Patient> model = initializePatients();

		view.fillObjectiveList(model);
	}

	private List<Patient> initializePatients() {
		EntityManager em = DB.getEntityManager();

		TypedQuery<Patient> q1 = em.createQuery("select m from Patient m", Patient.class);

		List<Patient> patientList = q1.getResultList();
		if (!patientList.isEmpty()) {
			return patientList;
		}

		return null;
	}

	@Override
	public void buttonClick() {	
	}
}
