package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int pid;

	@Temporal(TemporalType.DATE)
	private Date birthdate;
	private String city;

	@OneToOne(cascade = CascadeType.ALL)
	private DiagnosisList diagnosisList;
	
	@Transient
	private PatientHistory history = new PatientHistory();

	@OneToOne(cascade = CascadeType.ALL)
	private DrugList drugList = new DrugList();

	private String gender;

	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	private NoteList noteList = new NoteList();

	@OneToOne(cascade = CascadeType.ALL)
	private ObjectiveList objectiveList = new ObjectiveList();

	private byte[] picture;

	private String prename;

	private String street;

	private String zip;

	public Patient(int pid, Date birthdate, String city, String gender, String name, byte[] picture, String prename,
			String street, String zip) {
		super();
		this.pid = pid;
		this.birthdate = birthdate;
		this.city = city;
		this.gender = gender;
		this.name = name;
		this.picture = picture;
		this.prename = prename;
		this.street = street;
		this.zip = zip;
	}

	public Patient() {
		
	}

	public void persist() {
		EntityManager em = DB.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(this);
		trans.commit();
	}
	
//	private void delete() {
//		EntityManager em = DB.getEntityManager();
//		EntityTransaction trans = em.getTransaction();
//		em.getTransaction().begin();
//		em.remove(this);
//		em.getTransaction().commit();
//	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public DiagnosisList getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(DiagnosisList diagnosisList) {
		this.diagnosisList = diagnosisList;
	}
	public PatientHistory getHistory() {
		return history;
	}

	public void setHistory(PatientHistory history) {
		this.history = history;
	}

	public DrugList getDrugList() {
		return drugList;
	}

	public void setDrugList(DrugList drugList) {
		this.drugList = drugList;
	}

	public NoteList getNoteList() {
		return noteList;
	}

	public void setNoteList(NoteList noteList) {
		this.noteList = noteList;
	}

	public ObjectiveList getObjectiveList() {
		return objectiveList;
	}

	public void setObjectiveList(ObjectiveList objectiveList) {
		this.objectiveList = objectiveList;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPrename() {
		return this.prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getFormattedBirthdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String formatedDate = formatter.format(this.birthdate);		
		return formatedDate;
	}

}