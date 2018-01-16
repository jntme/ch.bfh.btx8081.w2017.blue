package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * Presenter for Patient.
 *
 * @author gfels6, jntme
 */
public class PatientPresenter implements PatientView.PatientViewListener, Serializable {

    private static final long serialVersionUID = -1115951198091997322L;

    private Patient model = null;
    private PatientView view;

    public PatientPresenter(PatientView view) {
        this.view = view;
        view.setListener(this);
    }

    public void displayPatient(Patient patient) {
        this.model = patient;

        view.setTitle(model.getName(), model.getPrename());

        //todo add pictures!
        //view.setPicture(model.getPicture());

        view.setSubPresenter(model);
    }

    /**
     * Gets called by the view to reqest a certain patient.
     *
     * @param patientId
     */
    @Override
    public void requestPatientWithId(String patientId) {

        Patient p = DB.getObjectById(patientId, Patient.class, "pid");
        if (p != null) {
            this.displayPatient(p);
            view.setPatient(p);

        } else {
            view.patientNotFound();
        }
    }
}
