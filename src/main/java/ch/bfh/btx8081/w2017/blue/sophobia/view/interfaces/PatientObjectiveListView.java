package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;

/**
 * Defines the behavior of the PatientObjectiveList
 * @author ziegm1
 *
 */
public interface PatientObjectiveListView {
	
	public void fillObjectiveList(ObjectiveList objectiveList);
	public Objective getSelectedObjective();
	public void setPresenter(PatientObjectiveListPresenter presenter);
	
	// Listener for the event handling
	interface PatientObjectiveListViewListener {
		void buttonClick(char operation);
		
	}
	
	public void addListener(PatientObjectiveListViewListener listener);
	
}