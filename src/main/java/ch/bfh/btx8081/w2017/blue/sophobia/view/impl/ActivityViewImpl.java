package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityView;

public class ActivityViewImpl extends VerticalLayout implements ActivityView, View {
	private static final long serialVersionUID = 5021837901974634127L;
	
	private NavigationUI navUI;
	private ActivityViewListener presenter;
	private Label lblName = new Label();
	private FormLayout form = new FormLayout();
	private TextField nameTextField = new TextField("Name");
	private TextArea descriptionArea = new TextArea("Description");
	private Button saveButton = new Button("Save");

	public ActivityViewImpl(NavigationUI navUI) {

		// the reference back to the navigation to communicate with the other
		// view components
		this.navUI = navUI;
		this.presenter = null;

		lblName.setStyleName("header");
		this.addStyleName("noPadding");
		this.addComponent(lblName);

		form.setWidth(100.0f, Unit.PERCENTAGE);

		nameTextField.setIcon(VaadinIcons.USER);
		nameTextField.setRequiredIndicatorVisible(true);
		nameTextField.setWidth(100.0f, Unit.PERCENTAGE);
		form.addComponent(nameTextField);

		descriptionArea.setIcon(VaadinIcons.COMMENT);
		descriptionArea.setRequiredIndicatorVisible(true);
		descriptionArea.setWidth(100.0f, Unit.PERCENTAGE);
		form.addComponent(descriptionArea);

		this.addComponent(form);
		this.addComponent(saveButton);
		this.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
	}

	@Override
	public void setIscomplete(String isComplete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDescription(String description) {
		descriptionArea.setValue(description);
	}

	@Override
	public void setSubPresenter(Activity model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		lblName.setValue(name);
		nameTextField.setValue(name);
	}

	@Override
	public void clearView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(ViewChangeEvent event) {

		if (event.getParameters() == null || event.getParameters().isEmpty()) {
			this.patientAndObjectiveNotFound();
		} else {

			String parameter = event.getParameters();

			String[] params = parameter.split("/");

			// todo this whole else if needs to be reviewed and rewritten.
			if (params.length != 3) {
				this.patientAndObjectiveNotFound();

			} else {
				int pid = -1;
				int oid = -1;
				int aid = -1;

				try {
					pid = Integer.parseInt(params[0]);
					oid = Integer.parseInt(params[1]);
					aid = Integer.parseInt(params[2]);
				} catch (NumberFormatException ex) {
					this.patientAndObjectiveNotFound();
				}

				presenter.requestActivity(pid, oid, aid);
			}
		}
	}

	@Override
	public void patientAndObjectiveNotFound() {
		this.navUI.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
	}

	@Override
	public void setPresenter(ActivityViewListener presenter) {
		this.presenter = presenter;

	}

}
