package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */

public class PatientViewImpl extends CustomComponent implements PatientView {

	private Label lblStreet = new Label("Dummy Street 12");
	private Label lblCity = new Label("Dummyhausen");
	private Label lblZip = new Label("3000");
	private Label lblBirthdate = new Label("01.01.1337");
	private Label lblName = new Label("Jon Schnee");
	private Label lblGender = new Label("Männlich");
	private Label lblPatientView = new Label("Patienten Ansicht");
	
	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/dummyUserPic.jpg"));
	Image image = new Image(null, resource);

	public PatientViewImpl() {

		final GridLayout layout = new GridLayout(6,4);
		
		lblPatientView.setStyleName("header");
		image.setHeight("150");
		image.setWidth("150");
		
		layout.setSpacing(true);
		
		layout.addComponent(lblPatientView,0,0,5,0);
		layout.addComponent(image,0,1,1,3);
		layout.addComponent(new Label("Name:"),2,1);
		layout.addComponent(new Label("Geschlecht:"),2,2);
		layout.addComponent(new Label("Geburtstag:"),2,3);
		layout.addComponent(lblName,3,1);
		layout.addComponent(lblGender,3,2);
		layout.addComponent(lblBirthdate,3,3);
		layout.addComponent(new Label("Strasse:"),4,1);
		layout.addComponent(new Label("Stadt:"),4,2);
		layout.addComponent(new Label("PLZ:"),4,3);		
		layout.addComponent(lblStreet,5,1);
		layout.addComponent(lblCity,5,2);
		layout.addComponent(lblZip,5,3);
		
		setCompositionRoot(layout);

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
		if(gender.equals("m")){
			lblGender.setValue("Männlich");
		}
		else{
			lblGender.setValue("Weiblich");
		}

	}

}
