package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientContactView;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

public class PatientContactPresenter {
	
	private Patient model;
	private PatientContactView view;
	
	public PatientContactPresenter(Patient model, PatientContactView view) {
		this.view = view;
		this.model = model;
		
		view.setName(model.getName(), model.getPrename());
		view.setAddress(model.getCity(), model.getStreet(), model.getZip());
		view.setBirthdate(model.getBirthdate());
		view.setGender(model.getGender());
		
	}
	


}
