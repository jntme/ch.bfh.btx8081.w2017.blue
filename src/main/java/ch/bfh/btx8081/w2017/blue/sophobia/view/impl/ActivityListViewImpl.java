package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityListView;

public class ActivityListViewImpl extends Panel implements ActivityListView, ClickListener {
	private static final long serialVersionUID = -3140144466857083444L;

	private NavigationUI navUI = null;

	private final Label header = new Label("Aktivitäten");
	private Button bAdd = new Button(VaadinIcons.PLUS_CIRCLE);
	private Button bDelete = new Button(VaadinIcons.TRASH);

	private List<ActivityListViewListener> listeners = new ArrayList<>();
	private Grid<Activity> grid = new Grid<>();

	private Patient patient = null;
	private Objective objective = null;

	public ActivityListViewImpl(NavigationUI navUI) {
		this.navUI = navUI;

		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout1 = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();

		vLayout.addComponent(hLayout1);
		vLayout.addComponent(hLayout2);

		hLayout1.addComponents(header, bAdd, bDelete);

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setWidth(100.0f, Unit.PERCENTAGE);

		hLayout2.setWidth(100.0f, Unit.PERCENTAGE);
		hLayout2.addComponent(grid);

		grid.addColumn(Activity::getName).setCaption("Aktivität");
		grid.addColumn(Activity::getDescription).setCaption("Beschreibung");
		grid.addColumn(Activity::getComplete).setCaption("Status");

		grid.addItemClickListener(new ItemClickListener<Activity>() {
			private static final long serialVersionUID = -9107765887521817876L;

			public void itemClick(ItemClick<Activity> event) {
				if (event.getMouseEventDetails().isDoubleClick()) {
					navUI.getNavigator().navigateTo(NavigationUI.ACTIVITYVIEW + "/" + patient.getPid() + "/" + objective.getOid() + "/" + event.getItem().getAid());
				}
			}
		});

		this.setContent(vLayout);

	}

	@Override
	public void fillActivityList(ActivityList activityList) {
		grid.setItems(activityList.getActivities());

	}

	@Override
	public Activity getSelectedActivity() {
		Iterator<Activity> itr = grid.getSelectedItems().iterator();
		return itr.next();
	}

	@Override
	public void clearView() {
		this.grid.setItems(new ArrayList<>());
	}

	@Override
	public void addListener(ActivityListViewListener listener) {
		listeners.add(listener);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		for (ActivityListViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption().charAt(0));
		}

	}

	@Override
	public void setIsEnabled(boolean isEnabled) {
		this.setEnabled(isEnabled);
	}

	public void setPatientAndObjective(Patient patient, Objective model) {
		this.patient = patient;
		this.objective = model;

	}

}
