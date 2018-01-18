package ch.bfh.btx8081.w2017.blue.sophobia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains Patient history list
 *
 * @author Odaoj1, petim1
 */
public class PatientHistory implements Serializable {

    private static final long serialVersionUID = 8172949595927202415L;

    private ArrayList<String> history = new ArrayList<>();

    /**
     * generates the list of patient history from Array list
     * @return hist as ArrayList with patient history
     */
    public ArrayList<String> initPatientHistory() {

        ArrayList<String> hist = new ArrayList<>(
                Arrays.asList(
                		" 08.11.2017:\t Neues Ziel 'Wohnung sauber halten'\n",
						"10.11.2017:\t Neue Aktivität 'Wischen' zum Ziel 'Wohnung sauber halten'\n",     
						"20.11.2017:\t Neue Notiz: Arztbesuch mit Spitex\n",
						"30.11.2017:\t Neue Notiz: Spitalbesuch wegen Suizidversuch\n",
						"17.12.2017:\t Neue Notiz: Psychische Krise\n",
						"04.01.2018:\t Ziel 'Wohnung sauber halten' erfolgreich abgeschlossen."
                ));

        return hist;
    }

    /**
     * Displays the list of patient's histories
     */
    @Override
    public String toString() {
    	String ret = getPatientHistory().toString();
    	StringBuilder sb = new StringBuilder(ret);
    	sb.deleteCharAt(0);
    	sb.deleteCharAt(sb.length()-1);
    	ret = sb.toString();
    	ret = ret.replaceAll("[',']","");
        return ret;
    }

    /**
     * Displays two of the randomly selected patient histories from the List of Dummy patient history
     */
    public ArrayList<String> getPatientHistory() {
        this.history = initPatientHistory(); //new history for demo, remove for real use
        return history;
    }
}
