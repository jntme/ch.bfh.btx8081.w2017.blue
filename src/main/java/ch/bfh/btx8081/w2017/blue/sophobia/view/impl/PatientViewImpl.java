package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientContactPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientInfoPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientView;

/**
 * 
 * @author gfels6
 *
 */
public class PatientViewImpl extends VerticalLayout implements PatientView, View {
	private NavigationUI navUI = null;

	// ziegm1: passed mainLayout into the views for being able to trigger page changes
	private VerticalLayout mainLayout;
	
	private Label lblTitle = new Label("Jon Schnee");
	private Button btnShowContact = new Button(VaadinIcons.INFO_CIRCLE);
	private final PatientObjectiveListViewImpl oView;
	PatientInfoViewImpl pInfoView = new PatientInfoViewImpl();
	PatientContactViewImpl pContactView = new PatientContactViewImpl();
	final GridLayout gridLayout = new GridLayout(5, 6);

	// ziegm1: store into instance variable, because otherwise it gets lost
	private PatientInfoPresenter patientInfoPresenter;
	
	public PatientViewImpl(NavigationUI navUI) {
		
		// the reference back to the navigation to communicate with the other view components
		this.navUI = navUI;
		
		// ziegm1: moved initialization into the constructor, so that mainLayout can be passed
		// into the PatientObjectiveListViewImpl
		this.mainLayout = mainLayout;
		this.oView = new PatientObjectiveListViewImpl(mainLayout);
		new PatientObjectiveListPresenter(oView, mainLayout);
		
		this.setStyleName("noPadding");

		lblTitle.setStyleName("header");



		gridLayout.setSpacing(true);
		

        Button button = new Button("Go to Main View",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navUI.getNavigator().navigateTo(NavigationUI.OBJECTIVEVIEW);
            }
        });	
        
        gridLayout.addComponent(button, 1, 1);


		btnShowContact.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -1610492227149824003L;

			@Override
			public void buttonClick(ClickEvent event) {

				pContactView.center();
				UI.getCurrent().addWindow(pContactView);

			}
		});

		gridLayout.addComponent(lblTitle, 0, 0);
		gridLayout.addComponent(btnShowContact, 1, 0);

		gridLayout.setComponentAlignment(btnShowContact, Alignment.MIDDLE_CENTER);

		this.addComponent(gridLayout);
		this.addComponent(pInfoView);
		this.addComponent(oView);

	}



	@Override
	public void setPresenter(Patient model) {
		new PatientInfoPresenter(model, pInfoView);
		new PatientContactPresenter(model, pContactView);
		// ziegm1: removed patientObjectiveListPresenter, it is now bound to its own view
	}

	@Override
	public void setTitle(String name, String prename) {
		lblTitle.setValue(prename + " " + name);

	}

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm 1");
    }
}
