package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.SelectPatientView;

public class SelectPatientViewImpl extends VerticalLayout implements SelectPatientView, ClickListener, View {
	
	private NavigationUI navUI = null;

	ArrayList<SelectPatientClickListener> listeners = new ArrayList<SelectPatientClickListener>();
	private Grid<Patient> grid = new Grid<>();
	private Label lblTitle = new Label("Patientenauswahl");

	public SelectPatientViewImpl(NavigationUI navUI) {

		// the reference back to the navigation to communicate with the other view components
		this.navUI = navUI;

		this.setStyleName("noPadding");
		this.addComponent(lblTitle);
		this.addComponent(grid);
		lblTitle.setStyleName("header");

		grid.setSelectionMode(SelectionMode.SINGLE);

		grid.addColumn(Patient::getPrename).setCaption("Vorname");
		grid.addColumn(Patient::getName).setCaption("Nachname");
		grid.addColumn(Patient::getFormattedBirthdate).setCaption("Geburtstag");
		
		// if someone clicks on an item in the grid, it should forward the patient to the PatientPresenter.
		grid.addItemClickListener(new ItemClickListener<Patient>() {
			@Override
			public void itemClick(ItemClick<Patient> event) {
				navUI.getNavigator().navigateTo(NavigationUI.PATIENTVIEW + "/" + event.getItem().getPid().toString());
			}
		});
		
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
