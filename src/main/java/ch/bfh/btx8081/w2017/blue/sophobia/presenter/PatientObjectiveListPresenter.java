package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
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
		this.model = model;
		this.view = view;
		
		view.fillObjectiveList(model.getObjectiveList());
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(char operation) {
		// TODO Auto-generated method stub
	}

}