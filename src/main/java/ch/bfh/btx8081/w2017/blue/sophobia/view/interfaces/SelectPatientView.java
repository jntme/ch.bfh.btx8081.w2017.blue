package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

public interface SelectPatientView {

	public void fillObjectiveList(List<Patient> patientList);
	
	public void addListener(SelectPatientClickListener listener);
	
	interface SelectPatientClickListener{
		
		/**
		 * This method is called when a patient is clicked
		 */
		public void buttonClick();
	}

}
