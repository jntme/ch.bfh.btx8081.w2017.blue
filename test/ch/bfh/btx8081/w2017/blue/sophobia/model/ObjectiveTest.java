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

public class ObjectiveTest {
	
	
	/**
	 * @author gfels6
	 */
	@Test
	public void addObjective() {
		
		Patient p1 = new Patient();
		p1.setName("ObjTester");
		p1.setPrename("ObjTest");
		
		ObjectiveList objl = new ObjectiveList();
		objl.createObj("ObjectiveTest", "Testen der Funktionalität", 2);
		p1.setObjectiveList(objl);
		
		p1.persist();
		
		EntityManager em = DB.getEntityManager();

		Query q1 = em.createQuery("select p from Objective p");
		List<Objective> objectives = q1.getResultList();
		objectives = objectives.stream().filter(p -> p.getName().equals("ObjectiveTest")).collect(Collectors.toList());
		
		p1.delete();
		objl.getObjectives().get(0).delete();

		assertEquals("select should return exactly one entry", 1, objectives.size());
	}
	

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
		testObj.persist();
		int testObjId = testObj.getOid();
		
		// when
		testObj.delete();
		
		// then
		EntityManager em = DB.getEntityManager();
		Query query = em.createQuery("select o from Objective o where o.oid = :oid");
		query.setParameter("oid", testObjId);
		query.getSingleResult();
	}
	
}
