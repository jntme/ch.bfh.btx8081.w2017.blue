package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityListView;

public class ActivityListPresenter implements ActivityListView.ActivityListViewListener {
	
	private Objective model;
	private ActivityListView view;
	
	public ActivityListPresenter(Objective model, ActivityListView view) {
		this.model = model;
		this.view = view;
		
		view.fillActivityList(model.getActList());
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(char operation) {
		// TODO Auto-generated method stub
	}

}
