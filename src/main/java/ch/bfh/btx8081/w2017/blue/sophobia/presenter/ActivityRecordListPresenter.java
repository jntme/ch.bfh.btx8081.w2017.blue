package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordListView;

/**
 * Presenter for ActivityRecordList.
 *
 * @author ziegm1
 */
public class ActivityRecordListPresenter implements ActivityRecordListView.ActivityRecordListViewListener, Serializable {
	
	private static final long serialVersionUID = 5575825887032295486L;

	private Activity model;
	
	public ActivityRecordListPresenter(Activity model, ActivityRecordListView view) {
		this.model = model;
		view.fillActivityRecordList(model.getActRecList());
		view.addListener(this);
	}

	@Override
	public void deleteActivityRecord(ActivityRecord activityRecord) {
		this.model.getActRecList().removeActivityRecord(activityRecord);
	}
}
