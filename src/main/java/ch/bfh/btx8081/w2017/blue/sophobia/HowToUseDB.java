package ch.bfh.btx8081.w2017.blue.sophobia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

/**
 * This is a test class with the purpose to show the team how to use DB.class
 * and how we handle DB requests and persistence.
 * 
 * @author jntme
 */
public class HowToUseDB {

	public static void howToUseDb() {

		// first of all, you need to get the EntityManager from the db
		EntityManager em = DB.getEntityManager();

		// HOW TO READ SOMETHING OUT OF THE DB

		Query q1 = em.createQuery("select m from Patient m"); // this is JPQL language! -> take a look at the slides if you don't remember how to do it :)

		//get the first entry of the list
		List<Patient> patientList = q1.getResultList();
		Patient p = null;
		if(!patientList.isEmpty()) {
			p = patientList.get(0);

			System.out.println("Patient: " + p.getPrename()  + " " + p.getName() + "; " + p.getBirthdate() + "\n\n");
		}


		//do something with the full list
		if(!patientList.isEmpty()) {
			for(Patient pat : patientList) {

				System.out.println("Patient: " + pat.getPrename()  + " " + pat.getName() + "; " + pat.getBirthdate());
			}
		}

		// HOW TO PERSIST SOMETHING IN THE DB

		Query q2 = em.createQuery("select p from Patient p");
		List<Patient> patients = q2.getResultList();

		// delete all patients
		//		if(!patients.isEmpty()) {
		//			for(Patient p1 : patients) {
		//				p1.delete();
		//				System.out.println(p1.getPrename() + " deleted from DB.");
		//			}
		//		}

		// create two new patients
		if(patients.isEmpty()) {
			Patient babbel = new Patient();
			babbel.setName("Babbel");
			babbel.setPrename("Tim");
			babbel.setGender("m");
			babbel.setBirthdate(new Date(1995,3,15));
			babbel.setStreet("Weihergasse 7");
			babbel.setZip("3000");
			babbel.setCity("Bern");

			babbel.persist();

			Patient vader = new Patient();
			vader.setName("Vader");
			vader.setPrename("Hans");
			vader.setGender("m");
			vader.setBirthdate(new Date());
			vader.setStreet("Todesstern");
			vader.setZip("99999");
			vader.setCity("Universe");

			vader.persist();

			System.out.println("Added Tim & Vader!");
		}

	}
}
