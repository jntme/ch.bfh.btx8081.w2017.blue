package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Tests the class DB.
 *
 * @author jntme
 */
public class DBTest {

    @Test
    public void testGetEntityManager() {
        assertNotNull("Entitymanager should not be NULL.", DB.getEntityManager());
    }

    @Test
    public void testGetPatientById() {

        Patient p1 = new Patient();
        p1.setName("Leia2");
        p1.setPrename("Organa2");

        p1.persist();

        Patient p2 = DB.getObjectById(Integer.toString(p1.getPid()), Patient.class, "pid");

        assertEquals(p1, p2);

        p2.delete();
    }

    @Test
    public void testGetObjectiveById() {

        Objective o1 = new Objective();
        o1.setName("Obj1");
        o1.setDifficulty(9);
        o1.setDescription("just another description.");

        o1.persist();

        Objective o2 = DB.getObjectById(Integer.toString(o1.getOid()), Objective.class, "oid");

        assertEquals(o1, o2);

        o2.delete();
    }

    @Test
    public void testGetActivityById() {
        Objective o1 = new Objective();
        Activity a1 = new Activity();
        a1.setName("Act1");
        a1.setDescription("Act desc!");

        o1.getActList().addActivity(a1);

        o1.persist();

        Activity a2 = DB.getObjectById(Integer.toString(a1.getAid()), Activity.class, "aid");

        o1.delete();
        a2.delete();
    }
}
