package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PatientHistoryPresenter {
	/**
	 * Contains Patien history  list
	 * @author jemal
	 */

	private ArrayList<String> PatientHistoryList = new ArrayList<>(
			Arrays.asList("12.12.2017: added Medication   sertraline",
					"10.12.2017 :had  the first cognitive-behavioral therapy (CBT)",     
					"11.11.2017 :  had diagnosis Examination with  Spitex, Diagnosed with Dression",
					"08.06.2017   family report for Medical consultation Telephone conversation report",
					"15.03.2017   was not able to shop ,Confined to home for 48hours",
					"25.02.2017  slept too long,did not cook or clean house for 2 days"
					));
	
	
	/**
	 * generates the random list of patient histories from the existing Array list of Patient history
	 */
	public ArrayList<String> getPatientHistory(int numElement){
		Random random = new Random();
		int sizeArray = PatientHistoryList.size();


		ArrayList<String> phl = new ArrayList();
		for(int i = 0; i< numElement;i++){
			int rdmPhl = random.nextInt(sizeArray);
			String element = PatientHistoryList.get(rdmPhl);
			phl.add(element);
		}
		return PatientHistoryList;
	}
/**
 * Displays the list of patient's histories
 */	
	/*public String toString() {
		//return this.diagnosisList.toString();¨
	return	getDiagnosisList(3).toString();
		
	}
	*/
	/**
	 * Displays the list of patient's histories
	 * The two major diagnosis will be appearing always.
	 * One other accompanying diagnosis follows these two.
	 */
	public String toString() {
		String text0 = "Social Anxiety Disorder A, Persistent Depressive Disorder";
		String text ="";
		ArrayList<String> PatientHistoryList = getPatientHistory(1);
		
		for (int i=0; i< PatientHistoryList.size(); i++) {
			if(i != 0){
				text += ", ";
			}
			text += PatientHistoryList.get(i);
		}
		return text0 + "," +text ;
	}

}

					
