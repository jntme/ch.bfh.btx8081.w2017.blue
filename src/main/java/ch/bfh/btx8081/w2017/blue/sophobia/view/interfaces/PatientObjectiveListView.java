package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;

/**
 * Defines the behavior of the PatientObjectiveList
 *
 * @author ziegm1
 */
public interface PatientObjectiveListView {

    void fillObjectiveList(ObjectiveList objectiveList);

    Objective getSelectedObjective();

    void setPresenter(PatientObjectiveListPresenter presenter);

    void addListener(PatientObjectiveListViewListener listener);

    void clearView();

    // Listener for the event handling
    interface PatientObjectiveListViewListener {
        void deleteObjective(Objective objective);
    }

}