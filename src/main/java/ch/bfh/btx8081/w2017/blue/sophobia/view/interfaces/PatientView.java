package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

/**
 * Defines the PatientInfoView methods, which the Presenter can Use. Has the
 * goal to allow different View impls without changing the Presenter.
 * 
 * @author gfels6
 *
 */
public interface PatientView {

	void setSubPresenter(Patient model);

	void setTitle(String name, String prename);
	void setPatient(Patient patient);
	
	// gets triggered, if a patient was required which does not exist
    void patientNotFound();

	interface PatientViewListener {
		void requestPatientWithId(String patientId);
	}
	
	void setListener(PatientViewListener listener);
	void clearView();
}
