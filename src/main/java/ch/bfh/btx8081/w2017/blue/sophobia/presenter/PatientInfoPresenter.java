package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.NoteViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView.PatientInfoClickListener;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

import java.util.ArrayList;

import com.vaadin.ui.UI;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
/**
 * This class 
 * @author odaoj1, kybup1
 *
 */

public class PatientInfoPresenter implements PatientInfoClickListener{

	private Patient pat;
	private PatientInfoView patInfoView;

	public PatientInfoPresenter(Patient pat1, PatientInfoView patInfoView1){
		this.pat = pat1;
		this.patInfoView = patInfoView1;
		patInfoView.addListener(this);

		//TODO fix this, so it checks if diganosislist is empty before converting it to a string
		patInfoView.setDiagnosis(pat.getDiagnosisList().toString());

		patInfoView.setDrugs(pat.getDrugList().toString());
		patInfoView.fillNoteList(pat.getNoteList());
	}

	public void initPatInfoView(){
		//patInfoView.setDiagnosis(pat.getDiagnosisList().toString());
		patInfoView.setDrugs(pat.getDrugList().toString());
		patInfoView.fillNoteList(pat.getNoteList());
	}

	@Override
	public void buttonClick(int i) {
		switch (i) {
		case 1:
			createNote();
			break;
		case 2:
			deleteNote();
			break;
		case 3:
			openNote();
			break;
		}
	}
	private void createNote(){
		NoteViewImpl noteView = new NoteViewImpl();
		new NotePresenter(pat, noteView);
	}
	private void deleteNote(){
		Note note = patInfoView.getSelectedNote();
		pat.getNoteList().getNotes().remove(note);
		note.delete();
	}
	private void openNote(){
		Note selectedN = patInfoView.getSelectedNote();
		NoteViewImpl noteView = new NoteViewImpl();
		new NotePresenter(pat, selectedN, noteView);
	}
}
