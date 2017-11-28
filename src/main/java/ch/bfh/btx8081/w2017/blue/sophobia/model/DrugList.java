package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;


/**
* The class DrugList provides dummy-data for drugs
* to display in patient information.
*
* @author  petim1
* @version 1.0
* @since   24.11.2017 
*/
public class DrugList {
	//Variables for class - use
	ArrayList<String> drugs = new ArrayList<String>();
	int randNum, minimum = 0, maximum = 8;

	/**
	 * The method {@code initdrugs()} fills the arraylist with dummy-data
	 * and the random generator generates a number
	 * 
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

		//Generate random number
		randNum = minimum + (int) (Math.random() * maximum);

	}

	/**
	 * Overrides the {@code toString()} Method for our specific use
	 */
	@Override
	public String toString() {
		initdrugs();
		return drugs.get(randNum);
		//System.out.print(drugs.get(randNum));
		//return null;

	}
}
