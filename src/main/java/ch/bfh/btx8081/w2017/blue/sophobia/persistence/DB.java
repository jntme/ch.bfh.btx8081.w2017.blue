package ch.bfh.btx8081.w2017.blue.sophobia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

/**
 * This class handles the DB queries.
 *
 * @author jntme
 */
public class DB {
    private static String PERSISTENCE_UNIT_NAME = "sophobia";
    private static EntityManager em;

    // a sttic instance of the EntityManager so it acts kind of like a singleton
    static {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return em;
    }

    //TODO: it would be needed to add this on a close event on the whole program
    public static void close() {
        em.close();
    }

    /**
     * Returns a Object from the database based on a given oid.
     * In order to not create this method multiple times, it uses generics to inform the method which
     * class it should use. Because it is not possible to get the class name of a generic, it is needed
     * to deliver the actual class type as well, so it can be queried from the DB.
     *
     * @param oid          eg. 112
     * @param classType    the actuall ClassType
     * @param objectIDName the name of the id of the class
     * @return the Object or null if there is nothing found
     */
    public static <D> D getObjectById(String oid, Class<D> classType, String objectIDName) {

        int oidInt;

        //convert oid to int if possible
        try {
            oidInt = Integer.parseInt(oid);
        } catch (NumberFormatException err) {
            return null;
        }

        String queryString = String.format("select o from %s o where o.%s = :oid", classType.getSimpleName(), objectIDName);
        Query query = em.createQuery(queryString);
        query.setParameter("oid", oidInt);
        return (D) query.getSingleResult();
    }
}