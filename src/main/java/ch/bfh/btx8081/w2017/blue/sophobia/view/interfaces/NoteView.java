package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

/**
 * Defines the PatientInfoView methods, which the Presenter can use.
 * Has the goal to allow different View impls without changing the Presenter.
 * @author kybup1
 *
 */

public interface NoteView {
	
	void setTitle(String title);
	String getTitle();
	void setContent(String content);
	String getNoteContent();
	/**
	 * Enables the checkbox for DangerNote
	 */
    void enableDanger();
	void setDanger(boolean danger);
	boolean getDanger();
	/**
	 * Makes the checkbox to change the active status in a DangerNote visible
	 */
    void enableActive();
	void setActive(boolean active);
	boolean getActive();
	
	void addListener(NoteClickListener listener);
	
	interface NoteClickListener{
		
		/**
		 * This method is called when the Save button is pressed
		 */
        void buttonClick();
	}

}
