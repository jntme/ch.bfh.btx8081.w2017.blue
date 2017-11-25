package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NoteList {
	
	@Id
	@GeneratedValue
	private int nlid;
	
	@OneToMany(mappedBy= "noteList")
	private List<Note> notes;
	

}
