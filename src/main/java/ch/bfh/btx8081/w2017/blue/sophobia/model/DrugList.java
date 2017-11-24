package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.util.ArrayList;

public class DrugList {
	ArrayList<String> drugs = new ArrayList<String>();
	int randNum, minimum = 0, maximum = 7;

	public void initdrugs() {

		drugs.add("Medikament: Citalopram / Wirkstoff: Citalopram  / Dosis: 40mg");
		drugs.add("Medikament: Cipralex / Wirkstoff: Escitalopram / Dosis: 10mg");
		drugs.add("Medikament: Cipralex / Wirkstoff: Escitalopram / Dosis: 20mg");
		drugs.add("Medikament: Lyrica / Wirkstoff: Pregabalin / Dosis: 150mg");
		drugs.add("Medikament: Lyrica / Wirkstoff: Pregabalin / Dosis: 300mg");
		drugs.add("Medikament: Cymbalta / Wirkstoff: Duloxetin / Dosis: 30mg");
		drugs.add("Medikament: Cymbalta / Wirkstoff: Duloxetin / Dosis: 60mg");
		drugs.add("Medikament: Sertralin / Wirkstoff: Sertralin / Dosis: 50mg");

		randNum = minimum + (int) (Math.random() * maximum);

	}

	@Override
	public String toString() {
		initdrugs();
		return drugs.get(randNum);

	}
}
