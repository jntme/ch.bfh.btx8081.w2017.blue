package ch.bfh.btx8081.w2017.blue.sophobia.presenter;

import java.io.Serializable;
import java.util.List;

import ch.bfh.btx8081.w2017.blue.sophobia.Breadcrumb;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Delegates the data from the model to the view
 *
 * @author ziegm1, jntme, gfels6
 */
public class ObjectivePresenter implements ObjectiveView.ObjectiveViewListener, Serializable {

    private static final long serialVersionUID = -750261196162245751L;

    private Objective model = null;
    private ObjectiveView view;
    private Patient patient = null;
    private Breadcrumb breadcrumb = null;
    private boolean isNewObjective = false;

    public ObjectivePresenter(ObjectiveView view, Breadcrumb bc) {
        this.view = view;
        this.breadcrumb = bc;
        view.setPresenter(this);
        
    }

    public void setObjective(Objective objective) {
        this.model = objective;

        view.setName(model.getName());
        view.setDescription(model.getDescription());
        view.setDifficulty(model.getDifficulty());
        view.setSubPresenter(model);
        view.sendToActivityList(getPatient(), getModel());

    }

    /**
     * Loads a specific objective by oid from a patient, if both exist and actualizes the view.
     *
     * @param pid: the patient id
     * @param oid: the objective id
     */
    @Override
    public void requestObjectiveWithPatientAndId(int pid, int oid) {
        this.patient = DB.getObjectById(Integer.toString(pid), Patient.class, "pid");
        Objective objective = null;

        if (this.patient != null) {
            List<Objective> objList = this.patient.getObjectiveList().getObjectives();
            breadcrumb.setPatLoc(this.patient);

            // look for objective
            for (Objective obj : objList) {
                if (obj.getOid() == oid) {
                    objective = obj;
                    break;
                }
            }

            // if found, display it
            if (objective != null) {

            	breadcrumb.setObjLoc(objective);
                this.setObjective(objective);
                this.isNewObjective = false;
                return; // do not continue, if found & set
            }
        }

        view.patientAndObjectiveNotFound();

    }

    /**
     * Prepares the presenter for a new Objective. saves the pid on this for
     * later use.
     *
     * @param pid: the corresponding patient id
     */
    @Override
    public void initNewObjective(int pid) {
        this.patient = DB.getObjectById(Integer.toString(pid), Patient.class, "pid");
        this.isNewObjective = true;
        ActivityList actList = new ActivityList();
        Objective newObj = new Objective();
        newObj.setActList(actList);
        setObjective(newObj);
    }

    @Override
    public void setObjectiveName(String value) {
        this.model.setName(value);
    }

    @Override
    public void setObjectiveDescription(String value) {
        this.model.setDescription(value);
    }

    @Override
    public void setObjectiveDifficulty(int value) {
        this.model.setDifficulty(value);
    }

    /**
     * Gets called, if the current state of the objective should be saved (can
     * be new or already existing)
     */
    @Override
    public void save() {
        if (this.isNewObjective) {
            this.patient.getObjectiveList().addObjective(this.model);
            System.out.println("Persisting new Objective: " + this.model.toString());
            this.patient.persist();
            this.isNewObjective = false;

            this.view.addedObjective();
        } else {
            this.patient.persist();
            System.out.println(
                    "Persisting old objective: " + this.model.toString() + "on patient " + this.patient.toString());
        }
    }

    @Override
    public Objective getModel() {
        return this.model;
    }

    @Override
    public Patient getPatient() {
        return this.patient;
    }
}
