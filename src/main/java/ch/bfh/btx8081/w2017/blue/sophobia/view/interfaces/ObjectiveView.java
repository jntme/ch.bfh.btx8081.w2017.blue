package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

/**
 * Defines the behavior of the Objective
 * @author petim1 (ziegm1)
 *
 */
public interface ObjectiveView {
	
	// Listener for the event handling
	interface ObjectiveViewListener {
		void buttonClick(char operation);
	}
	
	public void addListener(ObjectiveViewListener listener);

}
