package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;

import javax.persistence.*;

/**
 * The class DrugList provides dummy-data for drugs to display in patient
 * information.
 *
 * @author petim1
 * @version 1.5
 * @since 29.11.2017
 */
@Entity
public class DrugList {
	// Variables for class - use
	@Id
	private int did;
	
	@Transient ArrayList<String> drugs = new ArrayList<String>();
	@Transient int randNo, minimum = 0, maximum = 8;

	private String med;
	
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

		
		// Generate random number and add to String (1x)
		randNo = minimum + (int) (Math.random() * maximum);
		this.med = drugs.get(randNo);
		
		// Generate random number and add to String (2x)
//		for (int i = 0; i < 3; i++) {
//			randNo = minimum + (int) (Math.random() * maximum);
//			med += drugs.get(randNo);
//
//
//		}

	}

	/**
	 * Overrides the {@code toString()} Method for our specific use
	 */
	@Override
	public String toString() {
		return med;

	}
}
