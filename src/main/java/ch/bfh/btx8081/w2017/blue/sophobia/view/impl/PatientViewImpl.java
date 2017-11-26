package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.sql.Date;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */

public class PatientViewImpl extends CustomComponent implements PatientView {

	final TextField name = new TextField();
	Button button = new Button("Click Me");

	public PatientViewImpl() {

		// Test

		final VerticalLayout layout = new VerticalLayout();

		name.setCaption("Type your name here:");

		button.addClickListener(e -> {
			layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
		});

		layout.addComponents(name, button);
		
		setCompositionRoot(layout);

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
