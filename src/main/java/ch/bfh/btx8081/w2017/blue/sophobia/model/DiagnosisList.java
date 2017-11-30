package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DiagnosisList {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int diagID;

	/*
	 * Contains Diagnosis list
	 */

	private ArrayList<String> diagnosisList = new ArrayList<>(
			Arrays.asList("Social Anxiety Disorder A", "Persistent Depressive Disorder,"));

	//Can we add a method? is it toString method?
//	public String getDiagnosis(){
//		Random random = new Random();
//		int sizeArray = diagnosisList.size();
//		int randNum = random.nextInt(sizeArray);
//		String element = diagnosisList.get(randNum);
//		return element;
//	}

	//Example
//	public ArrayList<String> getDiagnosisList(){
//		Random random = new Random();
//		int sizeArray = diagnosisList.size();
//
//		ArrayList<String> arl = new ArrayList();
//		for(int i = 0; i<=4;i++){
//			int randNum = random.nextInt(sizeArray);
//			String element = diagnosisList.get(randNum);
//			arl.add(element);
//		}
//		return arl;
//	}

	// Displays the list of patient's diagnosis.
	public String toString() {
		return this.diagnosisList.toString();
	}

}
