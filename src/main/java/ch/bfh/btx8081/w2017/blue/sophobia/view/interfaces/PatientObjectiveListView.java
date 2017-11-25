package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

/**
 * @author ziegm1
 *
 */
public interface PatientObjectiveListView {
	
	interface PatientObjectiveListViewListener {
		void buttonClick(char operation);
	}
	
	public void addListener(PatientObjectiveListViewListener listener);
}