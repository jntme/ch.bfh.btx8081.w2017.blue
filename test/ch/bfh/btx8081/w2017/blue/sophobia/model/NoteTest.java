package ch.bfh.btx8081.w2017.blue.sophobia.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

public class NoteTest {
	
	/**
	 * @author kybup1
	 */
	
	@Test
	public void checkTimeStamp(){
		Date today = new Date(Calendar.getInstance().getTime().getTime());
		Note note = new Note("Test", "Note for a test");
		
		assertTrue("Date of the created note is today", note.getDate().compareTo(today)==0);	
	}
	
	@Test(expected = NoResultException.class)
	public void deleteNote() {
		
		Note note = new Note("Testnote", "Just another test");
		persist(note);
		int noteId = note.getNid();
		
		note.delete();
		
		EntityManager em = DB.getEntityManager();
		Query query = em.createQuery("select n from Note n where n.nid = :nid");
		query.setParameter("nid", noteId);
		query.getSingleResult();
	}
	
	private void persist(Note note) {
		EntityManager em = DB.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(note);
		trans.commit();
	}
}
