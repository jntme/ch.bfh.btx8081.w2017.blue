package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientContactView;

/**
 * @author gfels6
 */
public class PatientContactPresenter {

    public PatientContactPresenter(Patient model, PatientContactView view) {
        view.setName(model.getName(), model.getPrename());
        view.setAddress(model.getCity(), model.getStreet(), model.getZip());
        view.setBirthdate(model.getBirthdate());

        view.setPicture(model.getPicture());
        view.setGender(model.getGender());
    }
}
