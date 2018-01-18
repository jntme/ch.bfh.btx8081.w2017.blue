package ch.bfh.btx8081.w2017.blue.sophobia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vaadin.server.VaadinService;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.DiagnosisList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.DrugList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.NoteList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

/**
 * This is a test class with the purpose to show the team how to use DB.class
 * and how we handle DB requests and persistence. It also adds dummy data to
 * the tool so there is initial data to display and process.
 *
 * @author jntme, petim1
 */
public class HowToUseDB {
    final static DateFormat SDF = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
    final static String BASEPATH = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

    public static void howToUseDb() {

        // first of all, you need to get the EntityManager from the db
        EntityManager em = DB.getEntityManager();

        // HOW TO READ SOMETHING OUT OF THE DB
        Query q1 = em.createQuery("select m from Patient m"); // this is JPQL language! -> take a look at the slides if you don't remember how to do it :)


        // HOW TO PERSIST SOMETHING IN THE DB
        Query q2 = em.createQuery("select p from Patient p");
        List<Patient> patients = q2.getResultList();
     
        //**********************************************************************
        //first patient

        //Set basic data for patient 1
        if (patients.isEmpty()) {
            Patient babbel = new Patient();
            babbel.setName("Babbel");
            babbel.setPrename("Tim");
            babbel.setGender("m");
            String dateString = "15.03.1995";

            try {
                babbel.setBirthdate(SDF.parse(dateString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            babbel.setStreet("Weihergasse 7");
            babbel.setZip("3000");
            babbel.setCity("Bern");

            Path path1 = Paths.get(BASEPATH + "/WEB-INF/images/avatar1.png");
            try {
                byte[] data = Files.readAllBytes(path1);
                babbel.setPicture(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //Set diagnoses
            DiagnosisList dl1 = new DiagnosisList();
            babbel.setDiagnosisList(dl1);

            //Set medication
            DrugList drugl = new DrugList();
            drugl.initdrugs();
            babbel.setDrugList(drugl);

            //Set init notes
            NoteList notel = new NoteList();
            notel.createNote("Aufräumen", "Wenn mal jemand Zeit hat, sollte man bei Tim zuhause mal wieder aufräumen", false);
            notel.createNote("Aggressivität", "Tim kann manchmal bei Überforderung sehr aggressiv werden.", true);
            babbel.setNoteList(notel);

            //Init objectives
            ObjectiveList objl = new ObjectiveList();
            objl.createObj("Einkaufen", "Kann alleine Einkaufen gehen", 10);
            objl.createObj("Aufräumen", "Kann Wohnung alleine sauber halten.", 6);
            babbel.setObjectiveList(objl);

            //Init activities for objective 1
            ActivityList actl1 = new ActivityList();
            actl1.createAct("Peinliche Situation", "Gurkenglas im Shop fallen lassen");
            actl1.createAct("Fragen", "Nach einer anderen Schuhgrösse im Shop fragen");
            objl.getObjectives().get(0).setActList(actl1);

            //Init activities for objective 2
            ActivityList actl2 = new ActivityList();
            actl2.createAct("Staubwischen", "Drei Mal die Woche Staub abwischen");
            actl2.createAct("Staubsaugen", "Ein Mal die Woche staubsaugen");
            objl.getObjectives().get(1).setActList(actl2);

            //Init ACtivity Record
            ActivityRecordList actRecList1 = new ActivityRecordList();
            Instant instant1 = Instant.parse("2017-12-03T10:15:30.00Z");
            actRecList1.createActivityRecord(Date.from(instant1), 2, "Tim konnte nicht nachfragen, er musste den Shop verlassen");
            Instant instant2 = Instant.parse("2017-12-10T10:15:30.00Z");
            actRecList1.createActivityRecord(Date.from(instant2), 5, "Als wir heute im Shop waren, getraute sich Tim nach einer anderen Schuhgrösse zu fragen");
            actl1.getActivities().get(1).setActRecList(actRecList1);

            babbel.persist();

            //**********************************************************************
            //Second patient

            //Set basic data for patient 2
            Patient zimmermann = new Patient();
            zimmermann.setName("Zimmermann");
            zimmermann.setPrename("Monika");
            zimmermann.setGender("w");
            String dateString2 = "19.06.1989";
            try {
                zimmermann.setBirthdate(SDF.parse(dateString2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            zimmermann.setStreet("Hauptstrasse 84");
            zimmermann.setZip("3020");
            zimmermann.setCity("Bern");

            //Add image to profile

            Path path2 = Paths.get(BASEPATH + "/WEB-INF/images/avatar2.png");
            try {
                byte[] data = Files.readAllBytes(path2);
                zimmermann.setPicture(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Set diagnoses
            DiagnosisList dl2 = new DiagnosisList();
            zimmermann.setDiagnosisList(dl2);

            //Set medication
            DrugList drugl2 = new DrugList();
            drugl2.initdrugs();
            zimmermann.setDrugList(drugl2);

            //Notes generieren
            NoteList notel2 = new NoteList();
            notel2.createNote("Suizidal", "Patientin Zimmermann ist seit einigen Tagen wieder suizidal", true);
            notel2.createNote("Arztwechsel", "Patientin Zimmermann hat ihren Psychiater gewechselt.", true);
            notel2.createNote("Einkauf", "Patientin Zimmermann möchte keine Produkte aus dem Discounter", false);
            zimmermann.setNoteList(notel2);

            //Ziele definierern
            ObjectiveList objl2 = new ObjectiveList();
            objl2.createObj("Telefonieren", "Kann mit Verwandten oder Bekannten längere Telefonate führen", 3);
            objl2.createObj("Aufräumen", "Kann Wohnung alleine sauber halten.", 7);
            objl2.createObj("Arzt", "Arzttermine selbstständig managen", 10);
            zimmermann.setObjectiveList(objl2);

            //Aktivitäten für erstes Ziel
            ActivityList actl3 = new ActivityList();
            actl3.createAct("Dauer", "Telefonate sollen länger als 30 Minuten dauern");
            actl3.createAct("Sinnvolle Gespräche", "Thema des Gesprächs soll nicht auf der Erkrankung beruhen");
            objl2.getObjectives().get(0).setActList(actl3);

            //Aktivitäten für zweites Ziel
            ActivityList actl4 = new ActivityList();
            actl4.createAct("Staubsaugen", "Mindestens zweimal pro Woche saugen");
            actl4.createAct("Geschirr", "Geschirrspülmaschine selbstständig ein- und ausräumen");
            objl2.getObjectives().get(1).setActList(actl4);

            //Aktivitäten für drittes Ziel
            ActivityList actl5 = new ActivityList();
            actl5.createAct("Termin vereinbaren", "Termin mit Arzt für halbjährliches Blutbild vereinbaren");
            actl5.createAct("Zusätzliches Leiden", "Arzt auf bereits längere andauernde Kopfschmerzen ansprechen");
            objl2.getObjectives().get(2).setActList(actl5);

            //Verlauf der Aktivitäten
            ActivityRecordList actRecList2 = new ActivityRecordList();
            Instant instant3 = Instant.parse("2017-12-03T10:15:30.00Z");
            actRecList2.createActivityRecord(Date.from(instant3), 2, "Frage bezüglich dem zusätzlichen Leiden wurde nicht gestellt. Patientin zu verängstigt");
            Instant instant4 = Instant.parse("2017-12-10T10:15:30.00Z");
            actRecList2.createActivityRecord(Date.from(instant4), 6, "Telefonat und zusätzlichen Termin wegen Kopfschmerzen vereinbart (mit Hilfe!).");
            actl3.getActivities().get(0).setActRecList(actRecList2);

            zimmermann.persist();

            System.out.println("Added Tim Babbel & Monika Zimmermann as Patients!");
        }

    }
}
