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
	
	private Label lblStreet = new Label("Dummy Street 12");
	private Label lblCity = new Label("Dummyhausen");
	private Label lblZip = new Label("3000");
	private Label lblBirthdate = new Label("01.01.1337");
	private Label lblName = new Label("Jon Schnee");
	private Label lblGender = new Label("Männlich");
	private Label lblTitle = new Label("Jon Schnee");
	private Button btnShowContact = new Button(VaadinIcons.INFO_CIRCLE);
	private final PatientObjectiveListViewImpl oView;
	PatientInfoViewImpl pInfoView = new PatientInfoViewImpl();
	final GridLayout gridLayout = new GridLayout(2, 1);
	final Window subWindow = new Window("Patienteninformation");
	final GridLayout popUpLayout = new GridLayout(6, 6);

	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/dummyUserPic.jpg"));
	Image image = new Image(null, resource);

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
		subWindow.setStyleName("addPadding");
		subWindow.setDraggable(false);
		image.setHeight("150");
		image.setWidth("150");

		gridLayout.setSpacing(true);
		
		popUpLayout.setSpacing(true);
		popUpLayout.addComponent(image, 0, 1, 1, 3);
		popUpLayout.addComponent(new Label("Name:"), 2, 1);
		popUpLayout.addComponent(new Label("Geschlecht:"), 2, 2);
		popUpLayout.addComponent(new Label("Geburtstag:"), 2, 3);
		popUpLayout.addComponent(lblName, 3, 1);
		popUpLayout.addComponent(lblGender, 3, 2);
		popUpLayout.addComponent(lblBirthdate, 3, 3);
		popUpLayout.addComponent(new Label("Strasse:"), 4, 1);
		popUpLayout.addComponent(new Label("Stadt:"), 4, 2);
		popUpLayout.addComponent(new Label("PLZ:"), 4, 3);
		popUpLayout.addComponent(lblStreet, 5, 1);
		popUpLayout.addComponent(lblCity, 5, 2);
		popUpLayout.addComponent(lblZip, 5, 3);
		
		subWindow.setContent(popUpLayout);

		btnShowContact.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -1610492227149824003L;

			@Override
			public void buttonClick(ClickEvent event) {

				subWindow.center();
				UI.getCurrent().addWindow(subWindow);

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
	public void setAddress(String city, String street, String zip) {
		lblStreet.setValue(street);
		lblCity.setValue(city);
		lblZip.setValue(zip);
	}

	@Override
	public void setBirthdate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String formatedDate = formatter.format(date);
		lblBirthdate.setValue(formatedDate);
	}

	@Override
	public void setPicture(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name, String prename) {
		lblName.setValue(prename + " " + name);

	}

	@Override
	public void setGender(String gender) {
		if (gender.equals("m")) {
			lblGender.setValue("Männlich");
		} else {
			lblGender.setValue("Weiblich");
		}

	}

	@Override
	public void setPresenter(Patient model) {
		patientInfoPresenter = new PatientInfoPresenter(model, pInfoView);
		// ziegm1: removed patientObjectiveListPresenter, it is now bound to its own view
	}

	@Override
	public void setTitle(String name, String prename) {
		lblTitle.setValue(prename + " " + name);

	}

}
