package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.sql.Date;

import com.vaadin.ui.Image;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */

public class PatientPresenter implements PatientView{
	
	private Patient model;
	private PatientView view;
	
	public PatientPresenter(Patient model, PatientView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void setAddress(String city, String street, String zip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBirthdate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPicture(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name, String prename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGender(String gender) {
		// TODO Auto-generated method stub
		
	}

}
