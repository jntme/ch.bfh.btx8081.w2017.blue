package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordListView;

/**
 * @author ziegm1
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
		//view.setPresenter(this);
	}

	@Override
	public void deleteActivityRecord(ActivityRecord activityRecord) {
		this.model.getActRecList().removeActivityRecord(activityRecord);
	}

}
