package ch.bfh.btx8081.w2017.blue.sophobia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.persistence.DB;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientObjectiveListViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientViewImpl;


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
        
    	// UI STUFF BEGIN --------------------
    	
    	final VerticalLayout layout = new VerticalLayout();
        
        ObjectiveList model = new ObjectiveList();
		PatientObjectiveListViewImpl view = new PatientObjectiveListViewImpl();
		
		new PatientObjectiveListPresenter(model, view);
		
		
		Patient pModel = new Patient(null, null, null, null, null, null, null, null, null, null, null, null );
		PatientViewImpl pView = new PatientViewImpl();
		
		new PatientPresenter(pModel, pView);
		
		
		layout.addComponent(view);
		layout.addComponent(pView);
		
                
        setContent(layout);
        
        // take a look at the following class
        HowToUseDB.howToUseDb();
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
