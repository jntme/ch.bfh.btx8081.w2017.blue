package ch.bfh.btx8081.w2017.blue.sophobia;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientViewImpl;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	protected void init(VaadinRequest vaadinRequest) {

		// take a look at the following class
		HowToUseDB.howToUseDb();

		// ziegm1:
		// moved into the PatientPresenter and PatientObjectiveListPresenter
		// in MyUI not longer required
//		EntityManager em = DB.getEntityManager();
//		Query q1 = em.createQuery("select m from Patient m");
//		
//		List<Patient> patientList = q1.getResultList();
//		Patient patientZero = null;
//		if (!patientList.isEmpty()) {
//			patientZero = patientList.get(0);
//		}
		
		
		initViewAndPresenter();
	}

	// ziegm1:
	// Create initViewAndPresenter() as referred to Mister Vogel
	private void initViewAndPresenter() {
		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setStyleName("firstContainer");
		
		PatientViewImpl pView = new PatientViewImpl(mainLayout);
		new PatientPresenter(pView);
		
		mainLayout.addComponent(pView);
		
		setContent(mainLayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
