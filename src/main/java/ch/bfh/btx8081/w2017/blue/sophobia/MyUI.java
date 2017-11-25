package ch.bfh.btx8081.w2017.blue.sophobia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientObjectiveListViewImpl;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        ObjectiveList model = new ObjectiveList();
		PatientObjectiveListViewImpl view = new PatientObjectiveListViewImpl();
		
		new PatientObjectiveListPresenter(model, view);
		
		layout.addComponent(view);
        
        
        
        setContent(layout);
        
       EntityManager em = DB.getEntityManager();
//       EntityTransaction trans = em.getTransaction();
//       trans.begin();

       Query q = em.createQuery("select m from Patient m"); 
       
       List<Patient> patientList = q.getResultList(); 
       for(Patient p : patientList) {
    	  System.out.println(p.getPrename() + " " + p.getName()); 
       }

        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
