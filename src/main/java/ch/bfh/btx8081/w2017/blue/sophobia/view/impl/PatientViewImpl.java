package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientContactPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientInfoPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */
public class PatientViewImpl extends VerticalLayout implements PatientView {

	// ziegm1: passed mainLayout into the views for being able to trigger page changes
	private VerticalLayout mainLayout;
	
	private Label lblTitle = new Label("Jon Schnee");
	private Button btnShowContact = new Button(VaadinIcons.INFO_CIRCLE);
	private final PatientObjectiveListViewImpl oView;
	PatientInfoViewImpl pInfoView = new PatientInfoViewImpl();
	PatientContactViewImpl pContactView = new PatientContactViewImpl();
	final GridLayout gridLayout = new GridLayout(2, 1);
	
	
//	final Window subWindow = new Window("Patienteninformation");
	


	// ziegm1: store into instance variable, because otherwise it gets lost
	private PatientInfoPresenter patientInfoPresenter;
	
	public PatientViewImpl(VerticalLayout mainLayout) {
		// ziegm1: moved initialization into the constructor, so that mainLayout can be passed
		// into the PatientObjectiveListViewImpl
		this.mainLayout = mainLayout;
		this.oView = new PatientObjectiveListViewImpl(mainLayout);
		new PatientObjectiveListPresenter(oView, mainLayout);
		
		this.setStyleName("noPadding");

		lblTitle.setStyleName("header");



		gridLayout.setSpacing(true);
		


		btnShowContact.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -1610492227149824003L;

			@Override
			public void buttonClick(ClickEvent event) {

				pContactView.center();
				UI.getCurrent().addWindow(pContactView);

			}
		});

		gridLayout.addComponent(lblTitle, 0, 0);
		gridLayout.addComponent(btnShowContact, 1, 0);

		gridLayout.setComponentAlignment(btnShowContact, Alignment.MIDDLE_CENTER);

		this.addComponent(gridLayout);
		this.addComponent(pInfoView);
		this.addComponent(oView);

	}



	@Override
	public void setPresenter(Patient model) {
		new PatientInfoPresenter(model, pInfoView);
		new PatientContactPresenter(model, pContactView);
		// ziegm1: removed patientObjectiveListPresenter, it is now bound to its own view
	}

	@Override
	public void setTitle(String name, String prename) {
		lblTitle.setValue(prename + " " + name);

	}

}
