package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DB {
	private static String PERSISTENCE_UNIT_NAME = "sophobia";
	private static EntityManager em;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(Exception e) {
			System.err.println(e);
			System.exit(1);
		}


		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return em;
	}
	
	public static void close() {
		em.close();
	}
}
