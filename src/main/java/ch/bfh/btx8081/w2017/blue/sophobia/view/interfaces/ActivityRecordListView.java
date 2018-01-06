package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ActivityRecordListPresenter;

/**
 * Defines the behavior of the ActivityRecordList
 * @author ziegm1
 *
 */
public interface ActivityRecordListView {

	void fillActivityRecordList(ActivityRecordList activityRecordList);
	ActivityRecord getSelectedActivityRecord();
	void setPresenter(ActivityRecordListPresenter presenter);
	
	// Listener for the event handling
		interface ActivityRecordListViewListener {
			void deleteActivityRecord(ActivityRecord activityRecord);
		}
	
	void addListener(ActivityRecordListViewListener listener);
	void clearView();

	//void setIsEnabled(boolean isEnabled);

}
