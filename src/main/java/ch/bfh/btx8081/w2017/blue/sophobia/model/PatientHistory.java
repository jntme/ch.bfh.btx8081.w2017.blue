package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Contains Patient history list
 *
 * @author Odaoj1
 */
public class PatientHistory implements Serializable {

    private static final long serialVersionUID = 8172949595927202415L;

    private ArrayList<String> history = new ArrayList<>();

    /**
     * generates the random list of patient histories from the existing Array list of Patient history
     */
    public ArrayList<String> initPatientHistory(int numElement) {

        ArrayList<String> historyDummyList = new ArrayList<>(
                Arrays.asList(
                		"08.11.2017: Neues Ziel 'Wohnung sauber halten'",
						"10.11.2017: Neue Aktivität 'Wischen' zum Ziel 'Wohnung sauber halten'",     
						"20.11.2017: Arztbesuch mit Spitex",
						"30.11.2017: Spitalbesuch wegen Suizidversuch",
						"17.12.2017: Psychische Krise",
						"04.01.2018: Ziel 'Wohnung sauber halten' erfolgreich abgeschlossen."
                ));

        Random random = new Random();
        int sizeArray = historyDummyList.size();

        ArrayList<String> phl = new ArrayList<String>();
        for (int i = 0; i < numElement; i++) {
            int rdmPhl = random.nextInt(sizeArray);
            String element = historyDummyList.get(rdmPhl);
            phl.add(element);
        }

        return phl;
    }

    /**
     * Displays the list of patient's histories
     */
    public String toString() {
        return getPatientHistory().toString();
    }

    /**
     * Displays two of the randomly selected patient histories from the List of Dummy patient history
     */
    public ArrayList<String> getPatientHistory() {
        this.history = initPatientHistory(2); //new history for demo, remove for real use
        return history;
    }
}
