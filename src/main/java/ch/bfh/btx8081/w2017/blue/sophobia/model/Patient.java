package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Array;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pid;

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

	public Patient() {
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