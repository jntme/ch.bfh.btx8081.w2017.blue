package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityListView;

public class ActivityListPresenter implements ActivityListView.ActivityListViewListener, Serializable {
	
	private static final long serialVersionUID = 1287375477583183004L;
	
	private Objective model;
	
	public ActivityListPresenter(Objective model, ActivityListView view) {
		this.model = model;
		view.fillActivityList(model.getActList());
		
		view.setPresenter(this);
	}

	@Override
	public void deleteActivity(Activity activity) {
		this.model.getActList().removeActivity(activity);
	}
}
