package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.ActivityRecordViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordView;

/**
 * 
 * @author kybup1
 *
 */
public class ActivityRecordPresenterTest {

	@Test
	public void loadNewActivityRecord() {
		NavigationUI nav = new NavigationUI();
		ActivityRecordViewImpl view = new ActivityRecordViewImpl(nav);
		Patient pat = DB.getObjectById("1", Patient.class, "pid");
		Objective obj = pat.getObjectiveList().getObjectives().get(0);
		Activity act = obj.getActList().getActivities().get(0);
		ActivityRecord actRec = act.getActRecList().getActivityRecord().get(0);
		
		ActivityRecordPresenter presenter = new ActivityRecordPresenter(view);
		
		presenter.resolveIds(pat.getPid(), obj.getOid(), act.getAid(), -1);
		
		assertTrue("Date field is empty", view.getDate()==null);
	}
	
	@Test
	public void loadExistingActivityRecord() {
		NavigationUI nav = new NavigationUI();
		ActivityRecordViewImpl view = new ActivityRecordViewImpl(nav);
		Patient pat = DB.getObjectById("1", Patient.class, "pid");
		Objective obj = pat.getObjectiveList().getObjectives().get(0);
		Activity act = obj.getActList().getActivities().get(0);
		ActivityRecord actRec = act.getActRecList().getActivityRecord().get(0);
		
		ActivityRecordPresenter presenter = new ActivityRecordPresenter(view);
		
		presenter.resolveIds(pat.getPid(), obj.getOid(), act.getAid(), actRec.getArId());
		
		assertTrue("Date field is empty", view.getDate().equals(actRec.getDate()));
	}

}
