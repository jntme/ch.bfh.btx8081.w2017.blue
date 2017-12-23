package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ObjectivePresenter;

/**
 * Defines the behavior of the Objective
 * @author ziegm1
 *
 */
public interface ObjectiveView {
	
	public void setOid(int oid);
	public void setDescription(String description);
	public void setDifficulty(int difficulty);
	public void setIscomplete(String iscomplete);
	public void setName(String name);

	interface ObjectiveViewListener {
		public void requestObjectiveWithPatientAndId(int pid, int oid);
		void initNewObjective(int pid);
	}
	
	public void setListener(ObjectiveViewListener listener);
	public void patientAndObjectiveNotFound();
	public void clearView();
	
}
