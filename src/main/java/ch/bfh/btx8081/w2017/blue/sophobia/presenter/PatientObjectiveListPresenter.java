package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

/**
 * Delegates the data from the model to the view
 * @author ziegm1
 *
 */
public class PatientObjectiveListPresenter implements PatientObjectiveListView.PatientObjectiveListViewListener{
	
	private Patient model;

    public PatientObjectiveListPresenter(Patient model, PatientObjectiveListView view) {
        this.model = model;

		view.fillObjectiveList(model.getObjectiveList());
		
		view.addListener(this);
		view.setPresenter(this);
	}

	@Override
	public void deleteObjective(Objective objective) {
    	this.model.getObjectiveList().removeObjective(objective);
	}
}