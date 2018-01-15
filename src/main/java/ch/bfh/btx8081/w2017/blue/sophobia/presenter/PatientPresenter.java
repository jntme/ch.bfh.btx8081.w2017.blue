package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */
public class PatientPresenter implements PatientView.PatientViewListener, Serializable {

	private static final long serialVersionUID = -1115951198091997322L;
	
	private Patient model = null;
	private PatientView view;
	
	public PatientPresenter(PatientView view) {
		this.view = view;
		view.setListener(this);
	}
	
	public void displayPatient(Patient patient) {
		this.model = patient;

		view.setTitle(model.getName(), model.getPrename());

		//view.setPicture(model.getPicture());
		
		view.setSubPresenter(model);
	}

	@Override
	public void requestPatientWithId(String patientId) {
		
		
		
		Patient p = DB.getObjectById(patientId, Patient.class, "pid");
		if(p != null) {
			this.displayPatient(p);

		//todo
		//not nice! should be revised and changed soon
			
		view.setPatient(p);

		} else {
			view.patientNotFound();
		}
	}
}
