package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.components.grid.ItemClickListener;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientHistoryView;

public class PatientHistoryViewImpl  extends CustomComponent implements PatientHistoryView{

	private Accordion acc = new Accordion();
	private TextArea txaPatientHistory = new TextArea();
	
	public PatientHistoryViewImpl() {
		this.setCompositionRoot(acc);
		
		txaPatientHistory.setEnabled(false);
		txaPatientHistory.setRows(2);
		txaPatientHistory.setSizeFull();
		
		acc.addTab(txaPatientHistory,"Patient History");
	}
	
	@Override
	public void clearView() {
		txaPatientHistory.setValue("");
	}
	
	@Override
	public void setPatientHistory(String history) {
			txaPatientHistory.setValue(history);
	}
		
	}


