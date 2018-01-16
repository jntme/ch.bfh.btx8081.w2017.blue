package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientHistoryView;

/**
 * Patient history  presenter
 *
 * @author odaoj1
 */
public class PatientHistoryPresenter {

    private Patient pat;
    private PatientHistoryView patHistView;

    public PatientHistoryPresenter(Patient pat1, PatientHistoryView patHistView1) {
        this.pat = pat1;
        this.patHistView = patHistView1;

        try {
            patHistView.setPatientHistory(pat.getHistory().toString());
        } catch (NullPointerException e) {
            patHistView.setPatientHistory("Not available");
        }
    }

    public void initPatInfoView() {
        patHistView.setPatientHistory(pat.getHistory().toString());
    }

}

					
