package ch.bfh.btx8081.w2017.blue.sophobia.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

public class ObjectiveTest {

	/**
	 * @author ziegm
	 */
	@Test(expected = NoResultException.class)
	public void deleteAnObjective() {
		// given
		Objective testObj = new Objective();
		testObj.setName("Name Test");
		testObj.setDescription("Beschreibung Test");
		testObj.setDifficulty(4);
		testObj.setComplete(true);
		persist(testObj);
		int testObjId = testObj.getOid();
		
		// when
		testObj.delete();
		
		// then
		EntityManager em = DB.getEntityManager();
		Query query = em.createQuery("select o from Objective o where o.oid = :oid");
		query.setParameter("oid", testObjId);
		query.getSingleResult();
	}
	
	private void persist(Objective objective) {
		EntityManager em = DB.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(objective);
		trans.commit();
	}
}
