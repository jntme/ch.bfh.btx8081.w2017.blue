package ch.bfh.btx8081.w2017.blue.sophobia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vaadin.server.VaadinService;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.DiagnosisList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.DrugList;
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
//		List<Patient> patientList = q1.getResultList();
//		Patient p = null;
//		if(!patientList.isEmpty()) {
//			p = patientList.get(0);
//
//			System.out.println("Patient: " + p.getPrename()  + " " + p.getName() + "; " + p.getBirthdate() + "\n\n");
//		}


		//do something with the full list
//		if(!patientList.isEmpty()) {
//			for(Patient pat : patientList) {
//
//				System.out.println("Patient: " + pat.getPrename()  + " " + pat.getName() + "; " + pat.getBirthdate());
//			}
//		}

		// HOW TO PERSIST SOMETHING IN THE DB

		Query q2 = em.createQuery("select p from Patient p");
		List<Patient> patients = q2.getResultList();

		//				 delete all patients
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

			String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
			Path path = Paths.get(basepath + "/WEB-INF/images/dummyUserPic.jpg");
			try {
				byte[] data = Files.readAllBytes(path);
				babbel.setPicture(data);
			} catch (IOException e) {
				e.printStackTrace();
			}

			DiagnosisList dl = new DiagnosisList();
			babbel.setDiagnosisList(dl);

			DrugList drugl = new DrugList();
			drugl.initdrugs();
			babbel.setDrugList(drugl);
			
			NoteList notel = new NoteList();
			
			notel.createNote("Aufräumen", "Wenn mal jemand Zeit hat, sollte man bei Tim zuhause mal wieder aufräumen", false);
			notel.createNote("manchmal Aggro", "Tim kann manchmal sehr aggressiv sein, wenn er mit der Situation überfordert ist.", true);
			notel.createNote("Note3", "Hier steht ein anderer, nicht allzuwichtiger Text", false);
			
			//WORKAROUND!!!!!!
//			List<Note> tryHard1 = notel.getNotes();
//			tryHard1.get(0).setNoteList(notel);
//			tryHard1.get(1).setNoteList(notel);
//			tryHard1.get(2).setNoteList(notel);
			//WORKAROUND END!!!!!
			
			babbel.setNoteList(notel);
			
			ObjectiveList objl = new ObjectiveList();
			objl.createObj("Einkaufen", "Kann alleine Einkaufen gehen", 9);
			objl.createObj("Aufräumen", "Kann Wohnung alleine sauber halten.", 10);
			
			//WORKAROUND!!!!!!
//			List<Objective> tryHard2 = objl.getObjectives();
//			tryHard2.get(0).setObjList(objl);
//			tryHard2.get(1).setObjList(objl);
			//WORKAROUND END!!!!!
			
			
			babbel.setObjectiveList(objl);
			
			ActivityList actl1 = new ActivityList();
			
			actl1.createAct("Peinliche Situation", "Wasserglas im Restaurant umschütten");
			actl1.createAct("Fragen", "Nach einer anderen Schuhgrösse im Shop fragen");
			
			objl.getObjectives().get(0).setActList(actl1);
			
			ActivityList actl2 = new ActivityList();
			
			actl2.createAct("Irgwass machen", "MACH WAS!!!!!!");
			actl2.createAct("Test", "Dies ist ein Test");
			
			objl.getObjectives().get(1).setActList(actl2);
			
			//babbel.setActivityList(actl);
			
			//Objective obj = new Objective();
			//obj.setActList(actl);
			//objl.getOlid();
			
//			ActivityRecordList actRecList = new ActivityRecordList();
//			actRecList.createActivityRecord(, 5);

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

			System.out.println("Added Tim & Vader as Patients!");
		}

	}
}
