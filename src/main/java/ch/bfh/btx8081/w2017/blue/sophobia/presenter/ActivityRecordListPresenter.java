package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordListView;

/**
 * @author ziegm
 *
 */
public class ActivityRecordListPresenter implements ActivityRecordListView.ActivityRecordListViewListener {
	
	private Activity model;
	private ActivityRecordListView view;
	
	public ActivityRecordListPresenter(Activity model, ActivityRecordListView view) {
		this.model = model;
		this.view = view;
		
		view.fillActivityRecordList(model.getActRecList());
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(char operation) {
		// TODO Auto-generated method stub
	}

}
