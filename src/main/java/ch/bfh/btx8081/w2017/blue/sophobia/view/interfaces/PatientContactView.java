package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.util.Date;

import com.vaadin.ui.Image;

/**
 * @author gfels6
 */
public interface PatientContactView {

    void setAddress(String city, String street, String zip);

    void setBirthdate(Date date);

    void setPicture(byte[] imageLocal);

    void setName(String name, String prename);

    void setGender(String gender);

    void clearView();

}
