package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

public class ActivityTest {

	@Test
	public void addActivity() {
		
		Patient p1 = new Patient();
		p1.setName("Tester");
		p1.setPrename("Test");
		p1.persist();
		
		ObjectiveList objl = new ObjectiveList();
		objl.createObj("Test", "Testen der Funktionalität", 2);
		
		ActivityList actl = new ActivityList();
		actl.createAct("Test", "Testen der Funktionalität");
		
		objl.getObjectives().get(0).setActList(actl);
		
		EntityManager em = DB.getEntityManager();

		Query q1 = em.createQuery("select p from Activity p");
		List<Activity> activities = q1.getResultList();
		activities = activities.stream().filter(p -> p.getName().equals("Test")).collect(Collectors.toList());
		
		p1.delete();

		assertEquals("select should return exactly one entry", 1, activities.size());
		
	}

}
