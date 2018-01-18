package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Manages list of patient Diagnosis
 *
 * @author Odaoj1, petim1
 */
@Entity
public class DiagnosisList implements Serializable {

    private static final long serialVersionUID = -7448485478047526501L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int diagID;

    // Contains Diagnosis list
    private ArrayList<String> diagnosisList = new ArrayList<>(
            Arrays.asList("Generalisierte Angststörung",
                    "Abwesenheit und Unaufmerksamkeit"));

    /**
     * generates the random list of patient Diagnosis from the existing Array list of Diagnosis
     *
     * @param numElement
     * @return ArrayList<String> with diagnoses
     */
    public ArrayList<String> getDiagnosisList(int numElement) {
        Random random = new Random();
        int sizeArray = diagnosisList.size();


        ArrayList<String> arl = new ArrayList<String>();
        for (int i = 0; i < numElement; i++) {
            int randNum = random.nextInt(sizeArray);
            String element = diagnosisList.get(randNum);
            arl.add(element);
        }
        return arl;
    }

    /**
     * Displays the list of patient's diagnosis
     * The two major diagnosis will be appearing always.
     * One other accompanying diagnosis follows these two.
     */
    @Override
    public String toString() {
        String text0 = "Sozialphobie Typ A\nDepressive Störung";
        StringBuffer buf = new StringBuffer();
        ArrayList<String> diagnosisList = getDiagnosisList(1);

        for (int i = 0; i < diagnosisList.size(); i++) {
            if (i != 0) {
                buf.append(", ");
            }
            buf.append(diagnosisList.get(i));
        }
        return text0 + "\n" + buf.toString();
    }
}
