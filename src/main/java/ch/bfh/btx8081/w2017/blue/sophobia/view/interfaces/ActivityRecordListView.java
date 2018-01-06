package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;

/**
 * @author ziegm
 *
 */
public interface ActivityRecordListView {

	void fillActivityRecordList(ActivityRecordList activityRecordList);

	ActivityRecord getSelectedActivityRecord();

    void clearView();

	void setIsEnabled(boolean isEnabled);

	// Listener for the event handling
	interface ActivityRecordListViewListener {
		void buttonClick(char operation);
	}

	void addListener(ActivityRecordListViewListener listener);
}
