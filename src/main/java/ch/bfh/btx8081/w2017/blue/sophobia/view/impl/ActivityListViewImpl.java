package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.ItemClickListener;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityListView;

public class ActivityListViewImpl extends Panel implements ActivityListView {
	private static final long serialVersionUID = -3140144466857083444L;

	private NavigationUI navUI = null;

	private final Label header = new Label("Aktivitäten");
	private Button btnAdd = new Button(VaadinIcons.PLUS_CIRCLE);
	private Button btnDelete = new Button(VaadinIcons.TRASH);

	private ActivityListViewListener presenter = null;
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

		hLayout1.addComponents(header, btnAdd, btnDelete);

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

		// the add button click event handler
		btnAdd.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				navUI.getNavigator().navigateTo(NavigationUI.ACTIVITYVIEW + "/" + patient.getPid() + "/"
						+ objective.getOid() + "/" + NavigationUI.NEW);
			}
		});

		// the delete button click event handler
		btnDelete.addClickListener(event -> {
			grid.getSelectedItems().forEach(activity -> this.presenter.deleteActivity(activity));
			grid.getSelectedItems().forEach(activity -> grid.getSelectedItems().remove(activity));
			grid.clearSortOrder();
			Notification notification = new Notification("Deleted successful.", "deletion");
			notification.setDelayMsec(1000);
			notification.show(navUI.getPage());
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
	public void setIsEnabled(boolean isEnabled) {
		this.setEnabled(isEnabled);
	}

	@Override
	public void setPresenter(ActivityListViewListener listener) {
		this.presenter = listener;
	}

	public void setPatientAndObjective(Patient patient, Objective model) {
		this.patient = patient;
		this.objective = model;

	}

}
