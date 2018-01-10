package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class PatientHistory {
	/**
	 * Contains Patien history  list
	 * @author jemal
	 */
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.TABLE)
	//private int diagID;
	
	private ArrayList<String> history = new ArrayList<>();
	
	/**
	 * generates the random list of patient histories from the existing Array list of Patient history
	 */
	public ArrayList<String> initPatientHistory(int numElement){
		
		ArrayList<String> historyDummyList = new ArrayList<>(
				Arrays.asList("12.12.2017: added Medication   sertraline",
						"10.12.2017 :had  the first cognitive-behavioral therapy (CBT)",     
						"11.11.2017 :  had diagnosis Examination with  Spitex, Diagnosed with Dression",
						"08.06.2017   family report for Medical consultation Telephone conversation report",
						"15.03.2017   was not able to shop ,Confined to home for 48hours",
						"25.02.2017  slept too long,did not cook or clean house for 2 days"
						));
		
		Random random = new Random();
		int sizeArray = historyDummyList .size();


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
	
	public ArrayList<String> getPatientHistory(){
		this.history = initPatientHistory(2); //new history for demo, remove for real use
		return history;
	}
	
	
}
