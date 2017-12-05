package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Implements the GUI elements from the part Objective
 * @author petim1 (ziegm1)
 *
 */
public class ObjectiveViewImpl extends CustomComponent implements ObjectiveView, ClickListener {

	private List<ObjectiveViewListener> listeners = new ArrayList<ObjectiveViewListener>();
	
	/**
	 * Listener for event handling
	 */
	@Override
	public void addListener(ObjectiveViewListener listener) {
		listeners.add(listener);	
	}
	
	/**
	 * Event handler
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		for(ObjectiveViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption().charAt(0));
		}
		
	}

}
