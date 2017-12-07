package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import com.vaadin.ui.UI;

import ch.bfh.btx8081.w2017.blue.sophobia.model.DangerNote;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.NoteViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.NoteView.NoteClickListener;

/**
 * This class controls a given implementation of the NoteView interface.
 * It contains two different constructors:
 * 1. Creating a new Note (with Note object as parameter)
 * 2. Editing an existing Note (without Note object as parameter)
 * @author kybup1
 *
 */

public class NotePresenter implements NoteClickListener {
	private Patient pat = new Patient();
	private Note note = new Note();
	private NoteViewImpl noteView = new NoteViewImpl();
	private boolean newNote;
	
	/**
	 * This constructor is used to load an existing note into the NoteView
	 * @param Patient who owns the Note
	 * @param Note which should be modified
	 * @param Instance of NoteViewImpl
	 */
	public NotePresenter(Patient pat1, Note note1, NoteViewImpl view1) {
		pat = pat1;
		note = note1;
		noteView = view1;
		noteView.addListener(this);
		newNote = false;
		
		noteView.setTitle(note.getTitle());
		noteView.setContent(note.getContent());
		if (DangerNote.class.isInstance(note)){
			DangerNote dNote = (DangerNote) note;
			noteView.setDanger(true);
			noteView.enableActive();
			noteView.setActive(dNote.isActive());
		} else {
			noteView.setDanger(false);
		}
		UI.getCurrent().addWindow(noteView);
	}
	
	/**
	 * 
	 * @param Patient for whom a new note should be created
	 * @param Instance of NoteViewImpl
	 */
	public NotePresenter(Patient pat1, NoteViewImpl view1) {
		pat = pat1;
		noteView = view1;
		noteView.addListener(this);
		newNote = true;
		
		noteView.enableDanger();
		UI.getCurrent().addWindow(noteView);
	}
	
	@Override
	public void buttonClick() {
		if(newNote){
			createNote();
		} else {
			updateNote();
		}
	
	}
	
	private void updateNote(){
		note.setTitle(noteView.getTitle());
		note.setContent(noteView.getNoteContent());
		if(DangerNote.class.isInstance(note)){
			DangerNote dNote = (DangerNote) note;
			dNote.setActive(dNote.isActive());
		}
		pat.persist();
	}
	private void createNote(){
		pat.getNoteList().createNote(noteView.getTitle(), 
				noteView.getNoteContent(), 
				noteView.getDanger());
		pat.persist();
	}
}
