package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */
public class PatientPresenter {

	private Patient model;
	private PatientView view;
	
	public PatientPresenter(PatientView view) {
		this.view = view;
		model = initializePatient();
		
		view.setTitle(model.getName(), model.getPrename());

		//view.setPicture(model.getPicture());
		
		view.setPresenter(model);
		
		
		
	}
	
	// ziegm1: moved from MyUI into the Presenter
	private Patient initializePatient() {
		EntityManager em = DB.getEntityManager();
		
		Query q1 = em.createQuery("select m from Patient m");
		
		List<Patient> patientList = q1.getResultList();
		if (!patientList.isEmpty()) {
			return patientList.get(0);
		}
		
		return null;
	}
}
