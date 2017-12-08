package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

public class DBTest {

	@Test
	public void testGetEntityManager() {
		assertNotNull("Entitymanager should not be NULL.", DB.getEntityManager());
	}
}
