package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DB {
	private static String PERSISTENCE_UNIT_NAME = "sophobia";
	private static EntityManager em;
	
	static {
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return em;
	}
	
	//TODO: need to add this on a close event on the whole program
	public static void close() {
		em.close();
	}
}
