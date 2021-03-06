package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextArea;

import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientHistoryView;

/**
 * implements/Displays Patient history list
 *
 * @author odaoj1
 */
public class PatientHistoryViewImpl extends CustomComponent implements PatientHistoryView {

    private static final long serialVersionUID = -5596956616187771640L;

    private Accordion acc = new Accordion();
    private TextArea txaPatientHistory = new TextArea();

    public PatientHistoryViewImpl() {
        this.setCompositionRoot(acc);

        txaPatientHistory.setReadOnly(true);
        txaPatientHistory.setRows(6);
        txaPatientHistory.setSizeFull();
        txaPatientHistory.setWordWrap(true);

        acc.addTab(txaPatientHistory, "Patientenverlauf");
    }

    @Override
    public void clearView() {
        txaPatientHistory.setValue("");
    }

    @Override
    public void setPatientHistory(String history) {
        txaPatientHistory.setValue(history);
    }
}


