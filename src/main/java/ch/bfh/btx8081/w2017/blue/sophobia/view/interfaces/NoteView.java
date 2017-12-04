package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

/**
 * Defines the PatientInfoView methods, which the Presenter can use.
 * Has the goal to allow different View impls without changing the Presenter.
 * @author kybup1
 *
 */

public interface NoteView {
	
	public void setTitle(String title);
	public String getTitle();
	public void setContent(String content);
	public String getNoteContent();
	/**
	 * Enables the checkbox for DangerNote
	 */
	public void enableDanger();
	public void setDanger(boolean danger);
	public boolean getDanger();
	/**
	 * Makes the checkbox to change the active status in a DangerNote visible
	 */
	public void enableActive();
	public void setActive(boolean active);
	public boolean getActive();
	
	public void addListener(NoteClickListener listener);
	
	interface NoteClickListener{
		
		/**
		 * This method is called when the Save button is pressed and has to decide
		 * if a new Note will be persistet or if an existing will be manipulated
		 * @param true = new Note to persist, false = existing Note to update
		 */
		public void buttonClick(boolean newN);
	}

}
