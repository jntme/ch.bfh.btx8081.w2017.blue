package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;
import java.util.Arrays;

public class DiagnosisList {

	/*
	 * Contains Diagnosis list
	 */

	private ArrayList<String> diagnosisList = new ArrayList<>(
			Arrays.asList("Diagnosis_1", "Diagnosis_2", " Diagnosis_3", " Diagnosis_3", " Diagnosis_4"));

	public DiagnosisList() {
		this.diagnosisList = diagnosisList;
	}

	// Displays the list of patient's diagnosis.
	public String toString() {
		return this.diagnosisList.toString();
	}

}
