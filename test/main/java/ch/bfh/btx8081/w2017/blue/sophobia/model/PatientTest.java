package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

public class PatientTest {

	@Test
	public void saveAPatient() {
		
		Patient p1 = new Patient();
		p1.setName("Leia2");
		p1.setPrename("Organa2");
		
		p1.persist();
		
		EntityManager em = DB.getEntityManager();

		Query q2 = em.createQuery("select p from Patient p");
		List<Patient> patients = q2.getResultList();
		patients = patients.stream().filter(p -> p.getName().equals("Leia2")).collect(Collectors.toList());
		
		p1.delete();

		assertEquals("select should return exactly one entry", 1, patients.size());
	}
}
