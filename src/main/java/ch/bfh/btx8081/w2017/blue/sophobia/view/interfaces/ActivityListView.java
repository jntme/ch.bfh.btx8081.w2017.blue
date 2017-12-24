package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;

public interface ActivityListView {

	void fillActivityList(ActivityList activityList);

	Activity getSelectedActivity();

    void clearView();

	void setIsEnabled(boolean isEnabled);

	// Listener for the event handling
	interface ActivityListViewListener {
		void buttonClick(char operation);
	}

	void addListener(ActivityListViewListener listener);
}