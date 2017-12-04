package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.sql.Date;

import com.vaadin.ui.Image;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientInfoViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientObjectiveListViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */

public class PatientPresenter {

	private Patient model;
	private PatientView view;
	

	public PatientPresenter(Patient model, PatientView view) {
		this.model = model;
		this.view = view;
		
		view.setTitle(model.getName(), model.getPrename());
		view.setName(model.getName(), model.getPrename());
		view.setAddress(model.getCity(), model.getStreet(), model.getZip());
		view.setBirthdate(model.getBirthdate());
		view.setGender(model.getGender());
		//view.setPicture(model.getPicture());
		
		view.setPresenter(model);
		
		
		// PHIIIIIIIIL Zwei Zeilen auskommentieren falls Patient Model ready
//		PatientInfoViewImpl pInfoView = new PatientInfoViewImpl();
//		new PatientInfoPresenter(model, pInfoView);
		
		
		
		// MICHÄÄÄÄÄÄÄÄÄÄ Zwei Zeilen auskommentieren falls Patient Model ready
//		PatientObjectiveListViewImpl oView = new PatientObjectiveListViewImpl();
//		new PatientObjectiveListPresenter(model, oView);
	}
	
	
	
	

}
