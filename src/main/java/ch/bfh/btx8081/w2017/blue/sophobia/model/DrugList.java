package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;

import javax.persistence.*;

/**
 * The class DrugList provides dummy-data for drugs to display in patient
 * information.
 *
 * @author petim1
 * @version 1.0
 * @since 24.11.2017
 */
@Entity
public class DrugList {
	// Variables for class - use
	@Id
	private int did;
	
	ArrayList<String> drugs = new ArrayList<String>();
	ArrayList<String> mainList = new ArrayList<String>();
	int randNo, minimum = 0, maximum = 8;
	
	public DrugList(){
		initdrugs();
	}
	
	

	/**
	 * The method {@code initdrugs()} fills the ArrayList with dummy-data and
	 * the random generator generates a number to add 3 drugs to a list that
	 * will be given back.
	 */
	public void initdrugs() {
		drugs.add("Medikament: Citalopram / Wirkstoff: Citalopram  / Dosis: 40mg");
		drugs.add("Medikament: Cipralex / Wirkstoff: Escitalopram / Dosis: 10mg");
		drugs.add("Medikament: Cipralex / Wirkstoff: Escitalopram / Dosis: 20mg");
		drugs.add("Medikament: Lyrica / Wirkstoff: Pregabalin / Dosis: 150mg");
		drugs.add("Medikament: Lyrica / Wirkstoff: Pregabalin / Dosis: 300mg");
		drugs.add("Medikament: Cymbalta / Wirkstoff: Duloxetin / Dosis: 30mg");
		drugs.add("Medikament: Cymbalta / Wirkstoff: Duloxetin / Dosis: 60mg");
		drugs.add("Medikament: Sertralin / Wirkstoff: Sertralin / Dosis: 50mg");

		// Generate random number and add to List
		for (int i = 0; i < 4; i++) {
			randNo = minimum + (int) (Math.random() * maximum);
			mainList.add(drugs.get(randNo));

		}

	}

	/**
	 * Overrides the {@code toString()} Method for our specific use
	 */
	@Override
	public String toString() {
		return mainList.toString();

	}
}
