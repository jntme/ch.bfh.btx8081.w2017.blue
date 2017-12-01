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
		//patInfoView.setDiagnosis(pat.getDiagnosisList().toString());
		patInfoView.setDrugs(pat.getDrugList().toString());
		patInfoView.fillNoteList(pat.getNoteList());
	}

	public void initPatInfoView(){
		//patInfoView.setDiagnosis(pat.getDiagnosisList().toString());
		patInfoView.setDrugs(pat.getDrugList().toString());
		patInfoView.fillNoteList(pat.getNoteList());
	}
	

	public void openNote(){
		//ArrayList<NoteList> notesList = pat.getNoteList();
	}

	@Override
	public void buttonClick() {
		// TODO Auto-generated method stub
		
	}




}
