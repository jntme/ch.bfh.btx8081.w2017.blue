package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
	private Label lblGender = new Label("male");
	

	public PatientViewImpl() {

		final GridLayout layout = new GridLayout(5,5);
		
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
