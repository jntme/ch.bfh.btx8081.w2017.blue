package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.sql.Array;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	private Array diagnosis;

	private Array drug;

	private String gender;

	private String name;

	private Array note;

	private Array objective;

	private byte[] picture;

	private String prename;

	private String street;

	private String zip;

	private static EntityManager em = DB.getEntityManager();
	
	public Patient(Date birthdate, String city, Array diagnosis, Array drug, String gender, String name,
			Array note, Array objective, byte[] picture, String prename, String street, String zip) {
		super();
		this.birthdate = birthdate;
		this.city = city;
		this.diagnosis = diagnosis;
		this.drug = drug;
		this.gender = gender;
		this.name = name;
		this.note = note;
		this.objective = objective;
		this.picture = picture;
		this.prename = prename;
		this.street = street;
		this.zip = zip;
	}
	
	public Patient() {
		
	}

	public void persist() {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(this);
		trans.commit();
	}
	
	public void delete() {
		em.getTransaction().begin();
		em.remove(this);
		em.getTransaction().commit();
	}

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

	public Array getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Array diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Array getDrug() {
		return this.drug;
	}

	public void setDrug(Array drug) {
		this.drug = drug;
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

	public Array getNote() {
		return this.note;
	}

	public void setNote(Array note) {
		this.note = note;
	}

	public Array getObjective() {
		return this.objective;
	}

	public void setObjective(Array objective) {
		this.objective = objective;
	}

	public byte[] getPicture() {
		return this.picture;
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

}