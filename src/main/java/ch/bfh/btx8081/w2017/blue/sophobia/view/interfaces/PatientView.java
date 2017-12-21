package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.util.Date;

import com.vaadin.ui.Image;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

/**
 * Defines the PatientInfoView methods, which the Presenter can Use. Has the
 * goal to allow different View impls without changing the Presenter.
 * 
 * @author gfels6
 *
 */

public interface PatientView {

	public void setPresenter(Patient model);

	public void setTitle(String name, String prename);
	public void setPatient(Patient patient);
	
	// gets triggered, if a patient was required which does not exist
	public void patientNotFound();

	interface PatientViewListener {
		public void requestPatientWithId(String patientId);
	}
	
	public void setListener(PatientViewListener listener);
	public void clearView();
}
