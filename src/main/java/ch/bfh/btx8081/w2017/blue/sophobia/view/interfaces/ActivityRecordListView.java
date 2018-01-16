package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ActivityRecordListPresenter;

/**
 * @author ziegm1
 */
public interface ActivityRecordListView {

    void fillActivityRecordList(ActivityRecordList activityRecordList);

    ActivityRecord getSelectedActivityRecord();

    void setPresenter(ActivityRecordListPresenter presenter);

    void addListener(ActivityRecordListViewListener listener);

    void clearView();

    // Listener for the event handling
    interface ActivityRecordListViewListener {
        void deleteActivityRecord(ActivityRecord activityRecord);
    }
}
