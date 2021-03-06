package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * The class DrugList provides dummy-data for drugs to display in patient
 * information.
 *
 * @author petim1
 */
@Entity
public class DrugList implements Serializable {

    private static final long serialVersionUID = -497510382119394655L;
    @Transient
    ArrayList<String> drugs = new ArrayList<String>();
    @Transient
    int randNo, minimum = 0, maximum = 6;
    // Variables for class - use
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int did;
    private String med;

    public DrugList() {
        initdrugs();
    }


    /**
     * The method {@code initdrugs()} fills the ArrayList with dummy-data and
     * the random generator generates a number to add 3 drugs to a list that
     * will be given back.
     */
    public void initdrugs() {
        drugs.add("Medikament: Citalopram / Wirkstoff: Citalopram  / Dosis: 40mg");
        drugs.add("Medikament: Cipralex / Wirkstoff: Escitalopram / Dosis: 20mg");
        drugs.add("Medikament: Lyrica / Wirkstoff: Pregabalin / Dosis: 300mg");
        drugs.add("Medikament: Cymbalta / Wirkstoff: Duloxetin / Dosis: 60mg");
        drugs.add("Medikament: Sertralin / Wirkstoff: Sertralin / Dosis: 50mg");
        drugs.add("Medikament: EFEXOR ER / Wirkstoff: Venlafaxin / Dosis: 150mg");
        drugs.add("Medikament: Escitalopram / Wirkstoff: Escitalopram / Dosis: 20mg");


        // Generate random number and add to String (1x)
        randNo = minimum + (int) (Math.random() * maximum);
        this.med = drugs.get(randNo);

    }

    /**
     * Overrides the {@code toString()} Method for our specific use
     */
    @Override
    public String toString() {
        return this.med;

    }
}
