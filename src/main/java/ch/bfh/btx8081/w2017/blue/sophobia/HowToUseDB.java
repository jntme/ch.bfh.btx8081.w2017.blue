package ch.bfh.btx8081.w2017.blue.sophobia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.DiagnosisList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.DrugList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Note;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
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

		//		 delete all patients
		//				if(!patients.isEmpty()) {
		//					for(Patient p1 : patients) {
		//						p1.delete();
		//						System.out.println(p1.getPrename() + " deleted from DB.");
		//					}
		//				}

		// create two new patients
		if(patients.isEmpty()) {
			Patient babbel = new Patient();
			babbel.setName("Babbel");
			babbel.setPrename("Tim");
			babbel.setGender("m");
			String dateString = "15.03.1995";
			DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.GERMAN);
			try {
				babbel.setBirthdate(format.parse(dateString));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			babbel.setStreet("Weihergasse 7");
			babbel.setZip("3000");
			babbel.setCity("Bern");
			
			
//			DiagnosisList dl = new DiagnosisList();
//			babbel.setDiagnosisList(dl);
			
//			DrugList drugl = new DrugList();
//			drugl.initdrugs();
//			babbel.setDrugList(drugl);
//			
//			NoteList notel = new NoteList();
//			
//			notel.createNote("Aufräumen", "Wenn mal jemand Zeit hat, sollte man bei Tim zuhause mal wieder aufräumen", false);
//			notel.createNote("manchmal Aggro", "Tim kann manchmal sehr aggressiv sein, wenn er mit der Situation überfordert ist.", true);
//			notel.createNote("Note3", "Hier steht ein anderer, nicht allzuwichtiger Text", false);
//			
//			ObjectiveList objl = new ObjectiveList();
//			objl.createObj("Einkaufen", "Kann alleine Einkaufen gehen", 9001);
//			objl.createObj("Aufräumen", "Kann Wohnung alleine sauber halten.", 10000);

//			Note note1 = new Note("Aufräumen", "Wenn mal jemand Zeit hat, sollte man bei Tim zuhause mal wieder aufräumen", false);
//			Note note2 = new Note("manchmal Aggro", "Tim kann manchmal sehr aggressiv sein, wenn er mit der Situation überfordert ist.", true);
//			Note note3 = new Note("Note3", "Hier steht ein anderer, nicht allzuwichtiger Text", false);

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
