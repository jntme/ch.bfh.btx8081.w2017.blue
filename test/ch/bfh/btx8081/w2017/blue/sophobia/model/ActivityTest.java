package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;


public class ActivityTest {

	/**
	 * @author jntme, ziegm, gfels6
	 */
	@Test
	public void addActivity() {
		
		Patient p1 = new Patient();
		p1.setName("Tester");
		p1.setPrename("Test");
		
		ObjectiveList objl = new ObjectiveList();
		objl.createObj("ActivityTest", "Testen der Funktionalität", 2);
		p1.setObjectiveList(objl);
		
		ActivityList actl = new ActivityList();
		actl.createAct("ActivityTest", "Testen der Funktionalität");
		objl.getObjectives().get(0).setActList(actl);
		
		p1.persist();
		
		EntityManager em = DB.getEntityManager();

		Query q1 = em.createQuery("select p from Activity p");
		List<Activity> activities = q1.getResultList();
		activities = activities.stream().filter(p -> p.getName().equals("ActivityTest")).collect(Collectors.toList());
		
		p1.delete();
		objl.getObjectives().get(0).delete();
		actl.getActivities().get(0).delete();

		assertEquals("select should return exactly one entry", 1, activities.size());
	}
	
	/**
	 * @author ziegm
	 */
	@Test(expected = NoResultException.class)
	public void deleteAnActivity() {
		// given
		Activity testAct = new Activity();
		testAct.setName("Name Test");
		testAct.setDescription("Beschreibung Test");
		testAct.setComplete(true);
		persist(testAct);
		int testActId = testAct.getAid();
		
		// when
		testAct.delete();
		
		// then
		EntityManager em = DB.getEntityManager();
		Query query = em.createQuery("select a from Activity a where a.aid = :aid");
		query.setParameter("aid", testActId);
		query.getSingleResult();
	}
	
	private void persist(Activity activity) {
		EntityManager em = DB.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(activity);
		trans.commit();
	}
}
