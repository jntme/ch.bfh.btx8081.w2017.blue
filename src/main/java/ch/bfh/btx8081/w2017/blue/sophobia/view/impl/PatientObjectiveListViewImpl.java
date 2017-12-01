package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

/**
 * Implements the GUI elements from the part PatientObjectiveList
 * @author ziegm1
 *
 */
public class PatientObjectiveListViewImpl extends Panel implements PatientObjectiveListView, ClickListener {
	
	private final Label DISPLAY = new Label("Ziele");
	private Button btnDelete;
	private Button btnAdd;
	
	private List<PatientObjectiveListViewListener> listeners = new ArrayList<PatientObjectiveListViewListener>();
	private Grid<Objective> grid = new Grid<>();
	
	/**
	 * Constructor creates a new grid for viewing the object list as a table and adds some formatting.
	 */
	public PatientObjectiveListViewImpl() {
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout1 = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();
		
		vLayout.addComponent(hLayout1);
		vLayout.addComponent(hLayout2);
		
		btnAdd = new Button(VaadinIcons.PLUS_CIRCLE);
		btnDelete = new Button(VaadinIcons.TRASH);
		
		hLayout1.addComponents(DISPLAY, btnAdd, btnDelete);
		
		grid.setSelectionMode(SelectionMode.SINGLE);
		hLayout2.addComponent(grid);   
        grid.addColumn(Objective::getName).setCaption("Ziel"); 
        grid.addColumn(Objective::isComplete).setCaption("Status");
				
		this.setContent(vLayout);
	}

	/**
	 * Adds all the items of an object list to the grid
	 */
	@Override
	public void fillObjectiveList(ObjectiveList objectiveList) {
		grid.setItems(objectiveList.getObjectives());
		System.out.println(objectiveList.getObjectives().get(0).getName());
	}
	
	/**
	 * Returns the objectives which are currently selected in the view
	 * @return currently selected objectives
	 */	
	@Override
	public Objective getSelectedObjective() {
		Iterator<Objective> itr = grid.getSelectedItems().iterator();
		return itr.next();
	}

	/**
	 * Listener for event handling
	 */
	@Override
	public void addListener(PatientObjectiveListViewListener listener) {
		listeners.add(listener);
	}

	/**
	 * Event handler
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		for(PatientObjectiveListViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption().charAt(0));
		}
		
	}

}