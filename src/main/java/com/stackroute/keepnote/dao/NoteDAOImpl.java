package com.stackroute.keepnote.dao;


import java.util.List;



import com.stackroute.keepnote.model.Note;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;




/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database
 * 					transaction. The database transaction happens inside the scope of a persistence
 * 					context.
 * */

@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
//	@PersistenceContext
//	EntityManager entityManager;


	private SessionFactory sf;

	@Autowired
	public NoteDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}
	

	public NoteDAOImpl() {
		super();
	}


	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {

		try {
			sf.getCurrentSession().save(note);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return false;
		
		
	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		sf.getCurrentSession().delete(sf.getCurrentSession().get(Note.class,noteId));
	    return true;
	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {
		List<Note> listcontact = sf.getCurrentSession().createQuery("from Note", Note.class).list();
		return listcontact;
	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		return sf.getCurrentSession().find(Note.class,noteId);

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		try {
					sf.getCurrentSession().update(note);
					return true;
				}catch (Exception e) {
					e.printStackTrace();
				}
					return false;
				

	}

}