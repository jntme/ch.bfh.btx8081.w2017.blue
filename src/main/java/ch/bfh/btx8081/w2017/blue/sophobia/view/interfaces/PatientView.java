package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.sql.Date;

import com.vaadin.ui.Image;

/**
 * Defines the PatientInfoView methods, which the Presenter can Use.
 * Has the goal to allow different View impls without changing the Presenter.
 * @author gfels6
 *
 */

public interface PatientView {
	
	public void setAddress(String city, String street, String zip);
	public void setBirthdate(Date date);
	public void setPicture(Image image);
	public void setName(String name, String prename);
	public void setGender(String gender);
	

}
