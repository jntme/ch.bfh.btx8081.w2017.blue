package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

/**
 * Defines the PatientInfoView methods, which the Presenter can use.
 * Has the goal to allow different View impls without changing the Presenter.
 * @author kybup1
 *
 */

public interface NoteView {
	
	public void setTitle(String title);
	public void setContent(String content);
	/**
	 * Enables the checkbox for DangerNote
	 */
	public void enableDanger();
	public void setDanger(boolean danger);
	/**
	 * Makes the checkbox to change the active status in a DangerNote visible
	 */
	public void enableActive();
	public void setActive(boolean active);
	
	interface NoteClickListener{
		public void buttonClick();
	}

}
