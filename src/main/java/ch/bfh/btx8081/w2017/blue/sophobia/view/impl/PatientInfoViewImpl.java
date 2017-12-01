package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.Iterator;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView;

/**
 * Implements the PatientInfoView interface with all coresponding vaadin components
 * @author kybup1
 *
 */
public class PatientInfoViewImpl extends CustomComponent implements PatientInfoView {
	private Accordion acc = new Accordion();
	private GridLayout noteGrid = new GridLayout(3,1);
	private TextArea txaDiagnosis = new TextArea();
	private Label txaDrugs = new Label();
	private Grid<Note> notes = new Grid<Note>();
	private Button btnAddNote = new Button("+");
	
	public PatientInfoViewImpl(){
		this.setCompositionRoot(acc);
		txaDiagnosis.setEnabled(false);
		noteGrid.addComponent(btnAddNote,2, 0,  2, 0);
		noteGrid.addComponent(notes, 0, 0, 1, 0);
		
		acc.addTab(txaDiagnosis, "Diagnosen");
		acc.addTab(txaDrugs, "Medikation");
		acc.addTab(noteGrid, "Notizen");
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
		System.out.println(drugs);
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
