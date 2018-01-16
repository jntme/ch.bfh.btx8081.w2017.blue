package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Manages all notes for a patient
 *
 * @author kybup1
 */
@Entity
public class NoteList implements Serializable {

    private static final long serialVersionUID = -4360081192053724870L;

    @Id
    @GeneratedValue
    private int nlid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Note> notes = new ArrayList<>();

    /**
     * Creates a new note.
     * Needs the title and the content of the note.
     * Depending on the value of the boolean a different kind of note will be created
     * true = DangerNote, false=Note
     * Furthermore the Systemtime is captured and saved
     */
    public void createNote(String title, String content, boolean danger) {
        if (danger) {
            DangerNote note = new DangerNote(title, content);
            notes.add(note);
        } else {
            Note note = new Note(title, content);
            notes.add(note);
        }
    }

    /**
     * Checks if a given note is a DangerNote and returns a boolean
     *
     * @param note a note
     * @return boolean, true = is a DangerNote
     */
    public boolean isDangerNote(Note note) {
        return DangerNote.class.isInstance(note);
    }

    public int getNlid() {
        return nlid;
    }

    public void setNlid(int nlid) {
        this.nlid = nlid;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }


}
