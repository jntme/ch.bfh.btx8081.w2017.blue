package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

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
	
	public static Patient getPatient(String pid) {
		
		int pidInt = -1;

		//convert pid to int
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
			System.err.println("Database inconsistant!");
			return null;
			
			//note: 
			//this is very poor error management. if this needs to be changed 
			//before a real life release ;-).
		}
		else {
			return patients.get(0);
		}
	}
}
