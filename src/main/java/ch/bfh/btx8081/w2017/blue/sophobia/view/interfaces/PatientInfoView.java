package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.sql.Date;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView.PatientInfoClickListener;

/**
 * Defines the PatientInfoView methods, which the Presenter can Use.
 * Has the goal to allow different View impls without changing the Presenter.
 * @author kybup1
 *
 */

public interface PatientInfoView {
	
	
	public void setDiagnosis(String diagnosis);
	public void setDrugs(String drugs);
	
	
	/**
	 * Fills the table on the view with the Notes given in the submitted notelist.
	 * @param noteList
	 */
	public void fillNoteList(NoteList noteList);
	/**
	 * returns the Note which is selected by the user
	 * @return Note
	 */
	public Note getSelectedNote();
	public void addListener(PatientInfoClickListener listener);
	public void clearView();
	
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
