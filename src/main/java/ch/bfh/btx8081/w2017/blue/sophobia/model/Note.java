package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;

/**
 * Represents notes with a title, a content and the date when their created/modified.
 *
 * @author kybup1
 */

@Entity
@Inheritance
@DiscriminatorColumn(name = "note_type")
public class Note implements Serializable {

    private static final long serialVersionUID = -3291282125469189415L;

    @Id
    @GeneratedValue
    private int nid;
    private String title;
    private String content;
    private Date date;

    public Note() {

    }

    /**
     * Constructor that fills the note object with all attributes.
     * Takes the current system date as date attribute.
     *
     * @param title
     * @param content
     */
    public Note(String title, String content) {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void persist() {
        EntityManager em = DB.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(this);
        trans.commit();
    }

    public void delete() {
        EntityManager em = DB.getEntityManager();
        // not used? EntityTransaction trans = em.getTransaction();
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
