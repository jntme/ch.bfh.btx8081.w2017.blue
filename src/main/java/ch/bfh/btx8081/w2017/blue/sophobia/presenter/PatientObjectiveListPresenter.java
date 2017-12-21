package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.ObjectiveViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

/**
 * Delegates the data from the model to the view
 * @author ziegm1
 *
 */
public class PatientObjectiveListPresenter implements PatientObjectiveListView.PatientObjectiveListViewListener{
	
	private Patient model;
	private PatientObjectiveListView view;

    public PatientObjectiveListPresenter(Patient model, PatientObjectiveListView view) {
		this.view = view;

        this.model = model;

		view.fillObjectiveList(model.getObjectiveList());
		
		view.addListener(this);
		view.setPresenter(this);
	}

	@Override
	public void buttonClick(char operation) {
		// TODO Auto-generated method stub
	}
}