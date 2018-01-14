package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;

/**
 * Defines the PatientInfoView methods, which the Presenter can Use.
 * Has the goal to allow different View impls without changing the Presenter.
 * @author kybup1
 *
 */
public interface PatientInfoView {
	
	void setDiagnosis(String diagnosis);
	void setDrugs(String drugs);
	
	/**
	 * Fills the table on the view with the Notes given in the submitted notelist.
	 */
    void fillNoteList(NoteList noteList);
    
	/**
	 * returns the Note which is selected by the user
	 * @return Note
	 */
    Note getSelectedNote();
	void addListener(PatientInfoClickListener listener);
	void clearView();
	
	interface PatientInfoClickListener{
		/**
		 * Method which has to capture clickEvents and forward them
		 * Different Cases:
		 * 1 Create Note
		 * 2 Delete Note
		 * 3 Open Note
		 * 
		 */
		void  buttonClick(int i);
	}
}
