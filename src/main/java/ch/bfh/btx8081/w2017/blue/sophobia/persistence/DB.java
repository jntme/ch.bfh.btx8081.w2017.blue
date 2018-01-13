package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

/**
 * This class handles the
 *
 * @author jntme
 */
public class DB {
	private static String PERSISTENCE_UNIT_NAME = "sophobia";
	private static EntityManager em;
	
	static {
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return em;
	}
	
	//TODO: it would be needed to add this on a close event on the whole program
	public static void close() {
		em.close();
	}

	/**
	 * Returns a patient from the database based on a given pid.
	 *
	 * @param pid
	 * @return the patient Object or null if there is nothing found
	 */
	public static Patient getPatient(String pid) {

		int pidInt;

		//convert pid to int if possible
		try {
			pidInt = Integer.parseInt(pid);
		}
		catch(NumberFormatException err) {
			return null;
		}
		
		Query query = em.createQuery("select p from Patient p where p.pid = :pid");
		query.setParameter("pid", pidInt);
		List<Patient> patients = query.getResultList();
		
		if(patients.isEmpty()) return null;
		else if(patients.size() > 1) {
			System.err.println("Database inconsistent!");
			return null;
			
			//note: 
			//this is very poor error management. if this needs to be changed 
			//before a real life release ;-).
		}
		else {
			return patients.get(0);
		}
	}

	/**
	 * Returns a Object from the database based on a given oid.
     * In order to not create this method multiple times, it uses generics to inform the method which
	 * class it should use. Because it is not possible to get the class name of a generic, it is needed
	 * to deliver the string representation for the class as well, so it can be queried from the DB.
	 *
	 * @param oid eg. 112
	 * @param stringRepresentation eg. "Patient" or "Activity"
	 * @return the Object or null if there is nothing found
	 */
	public static <D> D getObjectById(String oid, String stringRepresentation) {

		int oidInt;

		//convert oid to int if possible
		try {
			oidInt = Integer.parseInt(oid);
		}
		catch(NumberFormatException err) {
			return null;
		}

		String queryString = "select o from " + stringRepresentation + " o where o.oid = :oid";
		Query query = em.createQuery(queryString);

		query.setParameter("oid", oidInt);
		List<D> objects = query.getResultList();

		if(objects.isEmpty()) return null;
		else if(objects.size() > 1) {
			System.err.println("Database inconsistent!");
			return null;
		}
		else {
			return objects.get(0);
		}
	}
}
