package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

public interface PatientInfoView {
	
	public void setBirthdate(date);
	public void setDiagnosis(String);
	public void setDrugs(String);
	public void setAddress(String);
	public void fillNoteList(NoteList);
	public Note getSelectedNote();
	public void addListener();
	
	public Interface PatientInfoClickListener{
		public buttonClick();
	}
}
