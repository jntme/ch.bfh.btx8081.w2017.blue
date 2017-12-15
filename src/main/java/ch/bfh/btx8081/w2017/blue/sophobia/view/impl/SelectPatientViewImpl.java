package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.SelectPatientView;

public class SelectPatientViewImpl extends VerticalLayout implements SelectPatientView, ClickListener {

	ArrayList<SelectPatientClickListener> listeners = new ArrayList<SelectPatientClickListener>();
	private Grid<Patient> grid = new Grid<>();
	private Label lblTitle = new Label("Patientenauswahl");

	public SelectPatientViewImpl() {

		this.setStyleName("noPadding");
		this.addComponent(lblTitle);
		this.addComponent(grid);
		lblTitle.setStyleName("header");

		grid.setSelectionMode(SelectionMode.SINGLE);

		grid.addColumn(Patient::getPrename).setCaption("Vorname");
		grid.addColumn(Patient::getName).setCaption("Nachname");
		grid.addColumn(Patient::getFormattedBirthdate).setCaption("Geburtstag");
	}

	@Override
	public void fillObjectiveList(List<Patient> patientList) {

		grid.setItems(patientList);

	}

	@Override
	public void addListener(SelectPatientClickListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

}
