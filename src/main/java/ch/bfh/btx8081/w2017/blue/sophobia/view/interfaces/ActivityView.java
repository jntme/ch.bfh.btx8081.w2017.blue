package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;

public interface ActivityView {
	
	void setDescription(String description);
	void setIscomplete(String isComplete);
	void setSubPresenter(Activity model);
	void setName(String name);
	void clearView();
	
	 interface ActivityViewListener {
		 void requestActivity(int pid, int oid, int aid);
	 }
	
	void setPresenter(ActivityViewListener presenter);
	void patientAndObjectiveNotFound();

}
