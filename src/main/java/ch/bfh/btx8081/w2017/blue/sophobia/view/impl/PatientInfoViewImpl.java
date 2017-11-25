package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.Iterator;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView;

/**
 * Implements the PatientInfoView interface with all coresponding vaadin components
 * @author kybup1
 *
 */
public class PatientInfoViewImpl extends Panel implements PatientInfoView {
	private GridLayout grid = new GridLayout(2,7);
	private Label lblDiagnosis = new Label("Diagnose");
	private Label lblDrugs = new Label("Medikation");
	private Label lblNotes = new Label("Notizen");
	private TextArea txaDiagnosis = new TextArea();
	private TextArea txaDrugs = new TextArea();
	private Grid<Note> notes = new Grid<Note>();
	private Button btnAddNote = new Button("+");
	
	public PatientInfoViewImpl(){
		txaDiagnosis.setEnabled(false);
		txaDrugs.setEnabled(false);
		grid.addComponent(lblDiagnosis);
		grid.addComponent(txaDiagnosis);
		grid.addComponent(lblDrugs);
		grid.addComponent(txaDrugs);
		grid.addComponent(lblNotes);
		grid.addComponent(btnAddNote);
		grid.addComponent(notes,0,5,1,5);
		
		this.setContent(grid);
		
	}
	
	/**
	 * Sets the content in the diagnosis field
	 * @param diagnosis
	 */
	@Override
	public void setDiagnosis(String diagnosis) {
		txaDiagnosis.setValue(diagnosis);		
	}
	
	/**
	 * Sets the content of the drugs field
	 * @param drugs
	 */
	@Override
	public void setDrugs(String drugs) {
		txaDrugs.setValue(drugs);
	}
	
	/**
	 * Constructs a grid which contains all the notes in the given noteList
	 * Displays title, content and date of the note
	 * @param noteList
	 */
	@Override
	public void fillNoteList(NoteList noteList) {
		notes.setSelectionMode(SelectionMode.SINGLE);
		notes.setItems(noteList.getNotes());
		notes.addColumn(Note::getTitle).setCaption("Titel");
		notes.addColumn(Note::getContent).setCaption("Inhalt");
		notes.addColumn(Note::getDate).setCaption("Erstellt");
	}
	
	/**
	 * Returns the note which is currently selected in the view
	 * @return currently selected note
	 */
	@Override
	public Note getSelectedNote() {
		Iterator<Note> itr = notes.getSelectedItems().iterator();
		return itr.next();
	}

	@Override
	public void addListener() {
		
	}

	
}
