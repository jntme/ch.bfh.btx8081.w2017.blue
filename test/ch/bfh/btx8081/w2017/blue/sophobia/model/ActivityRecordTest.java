package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

public class ActivityRecordTest {
	
	/**
	 * @author gfels6
	 */
	@Test
	public void addActivityRecord() {
		
		Patient p1 = new Patient();
		p1.setName("ActivityRecordTester");
		p1.setPrename("ActivityRecordTest");
		
		ObjectiveList objl = new ObjectiveList();
		objl.createObj("ActivityRecordTest", "Testen der Funktionalität", 2);
		p1.setObjectiveList(objl);
		
		ActivityList actl = new ActivityList();
		actl.createAct("ActivityRecordTest", "Testen der Funktionalität");
		objl.getObjectives().get(0).setActList(actl);
		
        ActivityRecordList actRecList = new ActivityRecordList();
        Instant instant1 = Instant.parse("2017-12-03T10:15:30.00Z");
        actRecList.createActivityRecord(Date.from(instant1), 2, "ActivityRecordTest");
        actl.getActivities().get(0).setActRecList(actRecList);
		
		p1.persist();
		
		EntityManager em = DB.getEntityManager();

		Query q1 = em.createQuery("select p from ActivityRecord p");
		List<ActivityRecord> activityRecords = q1.getResultList();
		activityRecords = activityRecords.stream().filter(p -> p.getDescription().equals("ActivityRecordTest")).collect(Collectors.toList());
		
		p1.delete();
		objl.getObjectives().get(0).delete();
		actl.getActivities().get(0).delete();
		actRecList.getActivityRecord().get(0).delete();

		assertEquals("select should return exactly one entry", 1, activityRecords.size());
	}

	/**
	 * @author ziegm
	 */
	@Test(expected = NoResultException.class)
	public void deleteAnActivityRecord() {
		// given
		ActivityRecord testActRec = new ActivityRecord();
		Instant instant = Instant.parse("2017-10-03T10:15:30.00Z");
		testActRec.setDate(Date.from(instant));
		testActRec.setDescription("Beschreibung Test");
		testActRec.setSuccess(4);
		persist(testActRec);
		int testActRecId = testActRec.getArId();
		
		// when
		testActRec.delete();
		
		// then
		EntityManager em = DB.getEntityManager();
		Query query = em.createQuery("select ar from ActivityRecord ar where ar.arId = :arId");
		query.setParameter("arId", testActRecId);
		query.getSingleResult();
	}
	
	private void persist(ActivityRecord record) {
		EntityManager em = DB.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(record);
		trans.commit();
	}
}
