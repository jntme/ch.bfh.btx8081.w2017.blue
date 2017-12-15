package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.util.Date;

import com.vaadin.ui.Image;

public interface PatientContactView {
	
	public void setAddress(String city, String street, String zip);
	public void setBirthdate(Date date);
	public void setPicture(Image image);
	public void setName(String name, String prename);
	public void setGender(String gender);

}
