package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DBTest {

	@Test
	public void testGetEntityManager() {
		assertNotNull("Entitymanager should not be NULL.", DB.getEntityManager());
	}
}
