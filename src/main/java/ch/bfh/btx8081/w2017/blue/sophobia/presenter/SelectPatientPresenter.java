package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.SelectPatientView;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.SelectPatientView.SelectPatientClickListener;;

public class SelectPatientPresenter implements SelectPatientClickListener{

	private List<Patient> model;
	private SelectPatientView view;

	public SelectPatientPresenter(SelectPatientView view) {
		this.view = view;
		model = initializePatients();

		view.fillObjectiveList(model);

	}

	private List<Patient> initializePatients() {
		EntityManager em = DB.getEntityManager();

		Query q1 = em.createQuery("select m from Patient m");

		List<Patient> patientList = q1.getResultList();
		if (!patientList.isEmpty()) {
			return patientList;
		}

		return null;
	}

	@Override
	public void buttonClick() {
		// TODO Auto-generated method stub
		
	}

}
