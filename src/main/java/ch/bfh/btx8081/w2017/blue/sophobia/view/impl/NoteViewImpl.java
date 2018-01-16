package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.NoteView;


/**
 * This class implements the view in which a note can be edited or created.
 * The appearance of the view can differ between thre stages:
 * 1. Create new Note
 * 2. Modify Note
 * 3. Modify DangerNote
 *
 * @author kybup1
 */

public class NoteViewImpl extends Window implements NoteView, ClickListener {

    private static final long serialVersionUID = 7432762097763836018L;

    ArrayList<NoteClickListener> listeners = new ArrayList<NoteClickListener>();
    //	Components:
    Label lblTitle = new Label("Titel");
    Label lblContent = new Label("Inhalt");
    TextField txfTitle = new TextField();
    TextArea txaContent = new TextArea();
    CheckBox chkDanger = new CheckBox("Wichtig");
    CheckBox chkActive = new CheckBox("Aktiv");
    Button btnSave = new Button(VaadinIcons.CHECK);
    Button btnCancel = new Button(VaadinIcons.CLOSE_BIG);
    // 	Layouts:
    VerticalLayout vLay = new VerticalLayout();
    HorizontalLayout chkHLay = new HorizontalLayout();
    HorizontalLayout btnHLay = new HorizontalLayout();

    public NoteViewImpl() {
        btnSave.addClickListener(this);
        btnCancel.addClickListener(this);
        chkDanger.setEnabled(false);
        chkActive.setVisible(false);
        chkHLay.addComponents(chkDanger, chkActive);
        btnHLay.addComponents(btnSave, btnCancel);

        vLay.addComponents(lblTitle, txfTitle, lblContent, txaContent, chkHLay, btnHLay);

        this.setContent(vLay);
        this.setModal(true);
    }

    @Override
    public String getTitle() {
        return txfTitle.getValue();
    }

    @Override
    public void setTitle(String title) {
        txfTitle.setValue(title);
    }

    @Override
    public void setContent(String content) {
        txaContent.setValue(content);
    }

    @Override
    public String getNoteContent() {
        return txaContent.getValue();
    }

    @Override
    public void enableDanger() {
        chkDanger.setEnabled(true);
    }

    @Override
    public boolean getDanger() {
        return chkDanger.getValue();
    }

    @Override
    public void setDanger(boolean danger) {
        chkDanger.setValue(danger);
    }

    @Override
    public void enableActive() {
        chkActive.setVisible(true);
    }

    @Override
    public boolean getActive() {
        return chkActive.getValue();
    }

    @Override
    public void setActive(boolean active) {
        chkActive.setValue(active);
    }

    @Override
    public void addListener(NoteClickListener listener) {
        listeners.add(listener);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        if (event.getButton().getIcon().equals(VaadinIcons.CHECK)) {
            for (NoteClickListener listener : listeners) {
                listener.buttonClick();
            }
        }
		/*
		 * found by FindBug - does nothing - kybup1 
		else if(event.getButton().getIcon().equals(VaadinIcons.CLOSE_BIG)){
			
		} */
        this.close();
    }
}
