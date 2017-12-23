package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityListView;

public class ActivityListViewImpl extends Panel implements ActivityListView, ClickListener {
	
	private final Label header = new Label("Aktivitäten");
	private Button bDelete = new Button(VaadinIcons.PLUS_CIRCLE);
	private Button bAdd = new Button(VaadinIcons.TRASH);
	
	private List<ActivityListViewListener> listeners = new ArrayList<ActivityListViewListener>();
	private Grid<Activity> grid = new Grid<>();

	
	public ActivityListViewImpl(){
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout1 = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();
		
		vLayout.addComponent(hLayout1);
		vLayout.addComponent(hLayout2);
		
		hLayout1.addComponents(header, bAdd, bDelete);
		
		grid.setSelectionMode(SelectionMode.SINGLE);
		hLayout2.addComponent(grid);
		grid.addColumn(Activity::getName).setCaption("Aktivität");
		grid.addColumn(Activity::getDescription).setCaption("Beschreibung");
		grid.addColumn(Activity::getComplete).setCaption("Status");
		
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
		for(ActivityListViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption().charAt(0));
		}
		
	}

}
