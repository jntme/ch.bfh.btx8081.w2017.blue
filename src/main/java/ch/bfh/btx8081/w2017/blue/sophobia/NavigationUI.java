package ch.bfh.btx8081.w2017.blue.sophobia;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ObjectivePresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.SelectPatientPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.ObjectiveViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.PatientViewImpl;
import ch.bfh.btx8081.w2017.blue.sophobia.view.impl.SelectPatientViewImpl;

/**
 * The Navigation is the entrypoint and the 'canvas' of the application.
 * All other views are getting set and diplayed inside this class, it also
 * provides the functionality to select other views and by that, switch between 
 * them.
 * ##### gfels6 Mir müesse wahrschinlech am beschte e Main Class mit Container u so mache
 * ##### und eini nume mitem Navigator dinne, isch aues zimli vermischt hie ergo niso geil
 */
@Theme("mytheme")
public class NavigationUI extends UI {

	private static final long serialVersionUID = -7908495433511088212L;

	private Navigator navigator = null;
	
	private Button btnHome; 

	// these strings provide the URI for a configured view
	// e.g. '/#!patient' resolves to the PATIENTVIEW
	public static final String MAIN = "";
	public static final String SELECTPATIENTVIEW = "selectPatient";
	public static final String PATIENTVIEW = "patient";
	public static final String OBJECTIVEVIEW = "objective";

	// these strings are for special navigation purposes
	public static final String NEW = "new";

	private SelectPatientPresenter selectPatientPresenter = null;
	private PatientPresenter patientPresenter = null;
	private ObjectivePresenter objPresenter = null;

	protected void init(VaadinRequest vaadinRequest) {
		
		btnHome = new Button(VaadinIcons.HOME);
		
		final VerticalLayout container = new VerticalLayout();
		final VerticalLayout content = new VerticalLayout();
		this.addStyleName("containerStyle");
		content.addStyleName("noPadding");
		container.addComponent(btnHome);
		container.addComponent(content);
		setContent(container);
		
		getPage().setTitle("Sophobia - finally free.");

        // take a look at the following class
		HowToUseDB.howToUseDb();
		


		// Create a navigator to control the views
		navigator = new Navigator(this, content);

		// Initiating the different views and their presenters and
		// adding the views on the navigator
		SelectPatientViewImpl selPatViewImpl = new SelectPatientViewImpl(this);
		this.selectPatientPresenter = new SelectPatientPresenter(selPatViewImpl);
		navigator.addView(MAIN, selPatViewImpl);
		navigator.addView(SELECTPATIENTVIEW, selPatViewImpl);

		PatientViewImpl patientViewImpl = new PatientViewImpl(this);
		this.patientPresenter = new PatientPresenter(patientViewImpl);
		navigator.addView(PATIENTVIEW, patientViewImpl);

		ObjectiveViewImpl objViewImpl = new ObjectiveViewImpl(this);
		this.objPresenter = new ObjectivePresenter(objViewImpl);
		navigator.addView(OBJECTIVEVIEW, objViewImpl);
		
		
		this.btnHome.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -6917620301709928610L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(NavigationUI.SELECTPATIENTVIEW);
			}
		});
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public PatientPresenter getPatientPresenter() {
		return patientPresenter;
	}

	public SelectPatientPresenter getSelectPatientPresenter() {
		return selectPatientPresenter;
	}

	public ObjectivePresenter getObjPresenter() {
		return objPresenter;
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = NavigationUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -9075165526789382344L;
	}
}
