package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

/**
 * @author ziegm1
 *
 */
public class PatientObjectiveListViewImpl extends CustomComponent implements PatientObjectiveListView, ClickListener {

	private Label display = new Label("0");
	private List<PatientObjectListViewListener> listeners = new ArrayList<PatientObjectiveListViewListener>();
	
	@Override
	public void buttonClick(ClickEvent event) {
		
	}

	@Override
	public void setDisplay(double disp) {
		
	}

	@Override
	public void addListener(PatientObjectiveListViewListener listener) {
		
	}

}
