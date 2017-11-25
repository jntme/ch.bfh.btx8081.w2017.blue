package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.NumberRenderer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

/**
 * @author ziegm1
 *
 */
public class PatientObjectiveListViewImpl extends CustomComponent implements PatientObjectiveListView, ClickListener {
	
	private final Label DISPLAY = new Label("Ziele");
	private final Button BTN_ADD = new Button ("Ziel hinzufuegen");
	private List<PatientObjectiveListViewListener> listeners = new ArrayList<PatientObjectiveListViewListener>();
	
	public PatientObjectiveListViewImpl() {
		GridLayout layout = new GridLayout(2,2);
		
		layout.addComponent(DISPLAY, 0, 0);
		layout.addComponent(BTN_ADD, 1, 1);
		
		Grid<Objective> grid = new Grid<>();
        grid.setSizeFull();
        grid.setWidth("300");
        grid.setSelectionMode(SelectionMode.NONE);
        
        layout.addComponent(grid, 0, 1);
		
        grid.addColumn(Objective::getOid, new NumberRenderer("%02d"))
        .setCaption("ID")
        .setExpandRatio(0);
        
        grid.addColumn(Objective::getName)
        .setCaption("Ziel")
        .setExpandRatio(2);
        
        grid.addColumn(Objective::isComplete)
        .setCaption("Status")
        .setExpandRatio(2);
				
		setCompositionRoot(layout);
	}

	

	@Override
	public void addListener(PatientObjectiveListViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		for(PatientObjectiveListViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption().charAt(0));
		}
		
	}

}