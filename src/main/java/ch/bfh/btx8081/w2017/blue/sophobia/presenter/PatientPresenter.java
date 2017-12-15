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

	private Patient model = null;
	private PatientView view;
	
	public PatientPresenter(PatientView view) {
		this.view = view;
	}
	
	public void displayPatient(Patient patient) {
		this.model = patient;

		view.setTitle(model.getName(), model.getPrename());

		//view.setPicture(model.getPicture());
		
		view.setPresenter(model);
		
	}
	
}
