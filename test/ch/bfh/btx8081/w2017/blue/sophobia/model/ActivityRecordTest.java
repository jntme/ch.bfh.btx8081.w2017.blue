package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

public class ActivityRecordTest {

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
