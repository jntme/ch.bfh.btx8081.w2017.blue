package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView.PatientInfoClickListener;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

import java.util.ArrayList;

import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;

/**
 * @author odaoj1
 *
 */

	public class PatientInfoPresenter implements PatientInfoClickListener {

	private Patient pat;
	private PatientInfoView patView;

	public PatientInfoPresenter(Patient pat, PatientInfoView patview) {
		this.pat = pat;
		this.patView = patview;
		initPatInfoView();
	}
	/**
	 * initializes Patient view
	 *
	 * @author odaoj1
	 *
	 */
	private void initPatInfoView() {

	}
	/**
	 * opens the List of Patient Notes
	 *
	 * @author odaoj1
	 *
	 */
	public void openNote() {
		NoteList noteList = pat.getNoteList();
		System.out.println(noteList.toString());
	}

	@Override
	public void buttonClick() {
		// TODO Auto-generated method stub

	}

}
