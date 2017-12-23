package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Manages list of patient Diagnosis
 * @author Odaoj1
 *
 */
@Entity
public class DiagnosisList {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int diagID;

	/**
	 * Contains Diagnosis list
	 */

	private ArrayList<String> diagnosisList = new ArrayList<>(
			Arrays.asList("Feeling that mind has gone blank",
					"Dizziness and lightheadedness"));
	

	/**
	 * generates the random list of patient Diagnosis from the existing Array list of Diagnosis
	 */
	public ArrayList<String> getDiagnosisList(int numElement){
		Random random = new Random();
		int sizeArray = diagnosisList.size();


		ArrayList<String> arl = new ArrayList();
		for(int i = 0; i< numElement;i++){
			int randNum = random.nextInt(sizeArray);
			String element = diagnosisList.get(randNum);
			arl.add(element);
		}
		return arl;
	}
/**
 * Displays the list of patient's diagnosis
 */	
	/*public String toString() {
		//return this.diagnosisList.toString();¨
	return	getDiagnosisList(3).toString();
		
	}
	*/
	/**
	 * Displays the list of patient's diagnosis
	 * The two major diagnosis will be appearing always.
	 * One other accompanying diagnosis follows these two.
	 */
	public String toString() {
		String text0 = "Social Anxiety Disorder A, Persistent Depressive Disorder";
		String text ="";
		ArrayList<String> diagnosisList = getDiagnosisList(1);
		
		for (int i=0; i< diagnosisList.size(); i++) {
			if(i != 0){
				text += ", ";
			}
			text += diagnosisList.get(i);
		}
		return text0 + "," +text ;
	}

}
