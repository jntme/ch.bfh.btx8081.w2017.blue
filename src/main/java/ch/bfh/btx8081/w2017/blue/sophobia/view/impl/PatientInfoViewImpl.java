package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientInfoView;

/**
 * Implements the PatientInfoView interface with all coresponding vaadin
 * components
 *
 * @author kybup1
 */
public class PatientInfoViewImpl extends CustomComponent implements PatientInfoView, ClickListener, ItemClickListener<Note> {

    private static final long serialVersionUID = -4387466349287712696L;

    private Accordion acc = new Accordion();

    private GridLayout noteGrid = new GridLayout(2, 2);
    private TextArea txaDiagnosis = new TextArea();
    private TextArea txaDrugs = new TextArea();
    private Grid<Note> notes = new Grid<Note>();
    private Button btnAddNote = new Button(VaadinIcons.PLUS_CIRCLE);
    private Button btnDeleteNote = new Button(VaadinIcons.TRASH);
    private PatientInfoClickListener listener;

    public PatientInfoViewImpl() {
        this.setCompositionRoot(acc);

        txaDiagnosis.setReadOnly(true);
        txaDiagnosis.setRows(3);
        txaDiagnosis.setSizeFull();
        
        txaDrugs.setReadOnly(true);
        txaDrugs.setRows(3);
        txaDrugs.setSizeFull();
        
        HorizontalLayout hLay = new HorizontalLayout();
        hLay.addComponents(btnAddNote, btnDeleteNote);
        noteGrid.setSizeFull();
        noteGrid.addComponent(hLay, 0, 0, 0, 0);
        noteGrid.addComponent(notes, 0, 1, 1, 1);

        btnDeleteNote.addClickListener(this);
        btnAddNote.addClickListener(this);
        notes.addItemClickListener(this);
        acc.addTab(txaDiagnosis, "Diagnosen");
        acc.addTab(txaDrugs, "Medikation");
        acc.addTab(noteGrid, "Notizen");

        noteGrid.setSizeFull();
        notes.setSizeFull();
        notes.addColumn(Note::getTitle).setCaption("Titel");
        notes.addColumn(Note::getContent).setCaption("Inhalt");
        notes.addColumn(Note::getDate).setCaption("Erstellt");
    }

    /**
     * Sets the content in the diagnosis field
     *
     * @param diagnosis
     */
    @Override
    public void setDiagnosis(String diagnosis) {
        txaDiagnosis.setValue(diagnosis);
    }

    /**
     * Sets the content of the drugs field
     *
     * @param drugs
     */
    @Override
    public void setDrugs(String drugs) {
        txaDrugs.setValue(drugs);
    }

    /**
     * Constructs a grid which contains all the notes in the given noteList
     * Displays title, content and date of the note
     *
     * @param noteList
     */
    @Override
    public void fillNoteList(NoteList noteList) {
        notes.setItems(noteList.getNotes());
        notes.setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Returns the note which is currently selected in the view
     *
     * @return currently selected note
     */
    @Override
    public Note getSelectedNote() {
        return notes.getSelectedItems().iterator().next();
    }

    @Override
    public void addListener(PatientInfoClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
        if (event.getButton().getIcon().equals(VaadinIcons.PLUS_CIRCLE)) {
            // for adding a note
            listener.buttonClick(1);
        } else if (event.getButton().getIcon().equals(VaadinIcons.TRASH)) {
            // for deleting a note
            listener.buttonClick(2);
        }

        this.notes.clearSortOrder();
    }

    @Override
    public void itemClick(ItemClick<Note> event) {
        if (event.getMouseEventDetails().isDoubleClick()) {
            notes.select(event.getItem());
            listener.buttonClick(3);
        }
    }

    @Override
    public void clearView() {
        txaDiagnosis.setValue("");
        txaDrugs.setValue("");
        notes.removeAllColumns();
    }
}
