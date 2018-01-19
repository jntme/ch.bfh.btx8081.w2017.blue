package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.Breadcrumb;
import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientContactPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientHistoryPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientInfoPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * View for patient.
 *
 * @author gfels6, jntme
 */
public class PatientViewImpl extends VerticalLayout implements PatientView, View {

	private static final long serialVersionUID = 2452620374969048563L;

	private NavigationUI navUI = null;
	private PatientViewListener listener = null;
	private Label lblTitle = new Label("");
	private Button btnShowContact = new Button(VaadinIcons.INFO_CIRCLE);

	private PatientObjectiveListViewImpl oView;
	private PatientHistoryViewImpl pHistory = new PatientHistoryViewImpl();
	private PatientInfoViewImpl pInfoView = new PatientInfoViewImpl();
	private PatientContactViewImpl pContactView = new PatientContactViewImpl();
	private GridLayout gridLayout = new GridLayout(5, 6);

	public PatientViewImpl(NavigationUI navUI) {

		// the reference back to the navigation to communicate with the other
		// view components
		this.navUI = navUI;

		this.oView = new PatientObjectiveListViewImpl(navUI);

		lblTitle.setStyleName("header");
		this.addStyleName("noPadding");

		gridLayout.setSpacing(true);

		this.btnShowContact.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1610492227149824003L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (pContactView.getParent() == null) {
					pContactView.center();
					UI.getCurrent().addWindow(pContactView);
				} else {
					// Window is still open
					// Do Nothing
				}
			}
		});

		gridLayout.addComponent(lblTitle, 0, 0);
		gridLayout.addComponent(btnShowContact, 1, 0);
		gridLayout.setHeight("90");

		gridLayout.setComponentAlignment(btnShowContact, Alignment.MIDDLE_CENTER);

		this.addComponent(gridLayout);
		this.addComponent(pHistory);
		this.addComponent(pInfoView);
		this.addComponent(oView);
	}

	@Override
	public void setSubPresenter(Patient model) {
		new PatientHistoryPresenter(model, pHistory);
		new PatientInfoPresenter(model, pInfoView);
		new PatientContactPresenter(model, pContactView);
		new PatientObjectiveListPresenter(model, oView);
	}

	@Override
	public void setTitle(String name, String prename) {
		lblTitle.setValue(prename + " " + name);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() == null || event.getParameters().isEmpty()) {
			patientNotFound();
		} else {
			listener.requestPatientWithId(event.getParameters());

		}
	}

	@Override
	public void setListener(PatientViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void patientNotFound() {
		this.navUI.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
	}

	public void setPatient(Patient patient) {
		this.oView.setPatient(patient);
	}

	/**
	 * Clears the view, so a next
	 */
	@Override
	public void clearView() {

		this.lblTitle.setValue("");
		this.oView.clearView();
		this.pHistory.clearView();
		this.pInfoView.clearView();
		// this.pContactView.clearView();
	}
}
