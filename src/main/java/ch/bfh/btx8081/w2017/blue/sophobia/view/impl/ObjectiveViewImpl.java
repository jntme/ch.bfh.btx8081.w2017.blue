package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Implements the GUI elements from the part Objective
 * 
 * @author ziegm1
 *
 */
public class ObjectiveViewImpl extends VerticalLayout implements ObjectiveView, View {
	private NavigationUI navUI = null;

	private ObjectiveViewListener listener = null;

	private Label lblName = new Label();
	private Label lblDescritpion = new Label("Beschreibung: ");
	private TextArea taDescription = new TextArea();
	// ActivityListViewImpl aView = new ActivityListViewImpl();
	final VerticalLayout layout = new VerticalLayout();
	final HorizontalLayout hLayout = new HorizontalLayout();
	private final ActivityListViewImpl aView;

	public ObjectiveViewImpl(NavigationUI navUI) {

		// the reference back to the navigation to communicate with the other
		// view components
		this.navUI = navUI;
		this.listener = null;

		this.setStyleName("noPadding");

		lblName.setStyleName("header");
		

		layout.addComponent(lblName);
		
		hLayout.addComponents(lblDescritpion, taDescription);
		layout.addComponent(hLayout);

		this.addComponent(layout);
		this.aView = new ActivityListViewImpl();
		this.addComponent(aView);
	}

	@Override
	public void setOid(int oid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDifficulty(int difficulty) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIscomplete(String iscomplete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		lblName.setValue(name);
	}
	
	@Override
	public void setDescription(String description) {
		taDescription.setValue(description);
	}

	@Override
	public void enter(ViewChangeEvent event) {

		if (event.getParameters() == null || event.getParameters().isEmpty()) {
			this.patientAndObjectiveNotFound();
		} else {

			String parameter = event.getParameters();

			String[] params = parameter.split("/");
			if (params.length != 2) {
				this.patientAndObjectiveNotFound();
			} else {
				int pid = -1;
				int oid = -1;

				try {
					pid = Integer.parseInt(params[0]);
					oid = Integer.parseInt(params[1]);
				} catch (NumberFormatException ex) {
					this.patientAndObjectiveNotFound();
				}

				listener.requestObjectiveWithPatientAndId(pid, oid);
			}
		}
	}

	@Override
	public void patientAndObjectiveNotFound() {
		this.navUI.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
	}

	@Override
	public void setListener(ObjectiveViewListener listener) {
		this.listener = listener;
	}
}
