package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientContactView;

import javax.xml.transform.stream.StreamSource;

/**
 * View for PatientContact.
 *
 * @author gfels6, jntme, ziegm
 */
public class PatientContactViewImpl extends Window implements PatientContactView {

    private static final long serialVersionUID = -7218135020495638066L;
    final GridLayout popUpLayout = new GridLayout(6, 6);
    Image image = new Image();
    private Label lblStreet = new Label("Dummy Street 12");
    private Label lblCity = new Label("Dummyhausen");
    private Label lblZip = new Label("3000");
    private Label lblBirthdate = new Label("01.01.1337");
    private Label lblName = new Label("Jon Schnee");
    private Label lblGender = new Label("Männlich");

    public PatientContactViewImpl() {

        image.setHeight("150");
        image.setWidth("150");

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

        this.setContent(popUpLayout);

        this.setStyleName("addPadding");
        this.setDraggable(false);
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

    public static StreamResource convertToImage(final byte[] imageData)
    {
        StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
            public InputStream getStream()
            {
                return (imageData == null) ? null : new ByteArrayInputStream(
                        imageData);
            }
        };

        return new StreamResource(streamSource, "streamedSourceFromByteArray");
    }

    @Override
    public void setPicture(byte[] imageLocal) {
        image.setSource(convertToImage(imageLocal));
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
    public void clearView() {
        // todo
    }
}
