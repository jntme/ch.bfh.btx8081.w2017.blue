package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Contains Patient history list
 * @author Jemal Oda
 */
public class PatientHistory implements Serializable {
	
	private static final long serialVersionUID = 8172949595927202415L;
	
	private ArrayList<String> history = new ArrayList<>();
	
	/**
	 * generates the random list of patient histories from the existing Array list of Patient history
	 */
	public ArrayList<String> initPatientHistory(int numElement){
		
		ArrayList<String> historyDummyList = new ArrayList<>(
				Arrays.asList(
						"12.12.2017: Medikamentenwechsel",
						"10.12.2017: Erste Kognitive Verhaltenstherapie (CBT)",     
						"11.11.2017: Arztbesuch mit Spitex",
						"08.06.2017: Spitalbesuch wegen Suizidversuch",
						"15.03.2017: Psychische Krise",
						"25.02.2017: Niedergeschlagen, keine Aktivitäten seit zwei Tagen"
						));

		Random random = new Random();
		int sizeArray = historyDummyList.size();

		ArrayList<String> phl = new ArrayList<String>();
		for(int i = 0; i< numElement;i++){
			int rdmPhl = random.nextInt(sizeArray);
			String element = historyDummyList .get(rdmPhl);
			phl.add(element);
		}
		
		return phl ;
	}
	
	/**
	 * Displays the list of patient's histories
	 */
	public String toString() {
		return	getPatientHistory().toString();
	}
	
	/**
	 * Displays two of the randomly selected patient histories from the List of Dummy patient history
	 */
	public ArrayList<String> getPatientHistory(){
		this.history = initPatientHistory(2); //new history for demo, remove for real use
		return history;
	}
}
