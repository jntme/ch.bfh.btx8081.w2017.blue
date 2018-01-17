package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ActivityRecordListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityView;

/**
 * Implements the view for an Activity.
 *
 * @author gfels6, jntme, ziegm1
 */
public class ActivityViewImpl extends VerticalLayout implements ActivityView, View {
    private static final long serialVersionUID = 5021837901974634127L;
    private final ActivityRecordListViewImpl aView;
    private NavigationUI navUI;
    private ActivityViewListener presenter;
    private Label lblName = new Label();
    private FormLayout form = new FormLayout();
    private TextField nameTextField = new TextField("Name");
    private TextArea descriptionArea = new TextArea("Beschreibung");

    private Button saveButton = new Button(VaadinIcons.CHECK);

    public ActivityViewImpl(NavigationUI navUI) {

        // the reference back to the navigation to communicate with the other
        // view components
        this.navUI = navUI;
        this.presenter = null;
        this.aView = new ActivityRecordListViewImpl(navUI);

        lblName.setStyleName("header");
        this.addStyleName("noPadding");
        this.addComponent(lblName);

        setupForm();

        setupSaveButton();

        this.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);

        this.addComponent(aView);
    }

    private void setupForm() {

        form.setWidth(100.0f, Unit.PERCENTAGE);

        nameTextField.setIcon(VaadinIcons.USER);
        nameTextField.setRequiredIndicatorVisible(true);
        nameTextField.setWidth(100.0f, Unit.PERCENTAGE);

        nameTextField.addValueChangeListener(event -> {
            this.presenter.setActivityName(event.getValue());
            this.lblName.setValue("Aktivität: " + event.getValue());
        });

        form.addComponent(nameTextField);

        descriptionArea.setIcon(VaadinIcons.COMMENT);
        descriptionArea.setRequiredIndicatorVisible(true);
        descriptionArea.setWidth(100.0f, Unit.PERCENTAGE);

        descriptionArea.addValueChangeListener(event -> this.presenter.setActivityDescription(event.getValue()));

        form.addComponent(descriptionArea);

        this.addComponent(form);
    }

    @Override
    public void setDescription(String description) {
        descriptionArea.setValue(description);
    }

    @Override
    public void setSubPresenter(Activity model) {
        new ActivityRecordListPresenter(model, aView);
    }

    @Override
    public void setName(String name) {
        lblName.setValue("Aktivität: " + name);
        nameTextField.setValue(name);
    }

    @Override
    public void addedActivity() {
        setViewOnExistingActivity();

        navUI.getNavigator().navigateTo(NavigationUI.ACTIVITYVIEW + "/" +
                this.presenter.getPatient().getPid() + "/" +
                this.presenter.getObjective().getOid() + "/" +
                this.presenter.getModel().getAid());

        Notification notification = new Notification("Neue Aktivität hinzugefügt!", "Erfolg");
        notification.setDelayMsec(1000);
        notification.show(navUI.getPage());
    }

    /**
     * Clears the view
     */
    @Override
    public void clearView() {
        lblName.setValue("");
        descriptionArea.setValue("");

        aView.clearView();
    }

    /**
     * Gets called when the view is called. The event contains information about the
     * URL parameter with which it is possible to get the actual activity which should
     * be processed.
     *
     * @param event
     */
    @Override
    public void enter(ViewChangeEvent event) {

        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            this.patientAndObjectiveNotFound();
        } else {

            String parameter = event.getParameters();

            String[] params = parameter.split("/");

            if (params.length != 3) {
                this.patientAndObjectiveNotFound();

            } else if (params[2].equals(NavigationUI.NEW)) {

                presenter.initNewActivity(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
                setViewOnNewActivity();
            } else {
                int pid = -1;
                int oid = -1;
                int aid = -1;

                try {
                    pid = Integer.parseInt(params[0]);
                    oid = Integer.parseInt(params[1]);
                    aid = Integer.parseInt(params[2]);
                } catch (NumberFormatException ex) {
                    this.patientAndObjectiveNotFound();
                }

                setViewOnExistingActivity();
                presenter.requestActivity(pid, oid, aid);
            }
        }
    }


    /**
     * Adjusts the view for a new activity.
     * (renames button to 'add', disables aView (...)
     */
    private void setViewOnNewActivity() {
        this.saveButton.setCaption("Add");
        this.aView.setEnabled(false);
    }

    /**
     * Adjusts the view for a new activity.
     * (renames button to 'add', disables aView (...)
     */
    private void setViewOnExistingActivity() {
        //this.saveButton.setCaption("Save");
        this.aView.setEnabled(true);
    }

    @Override
    public void patientAndObjectiveNotFound() {
        this.navUI.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
    }

    @Override
    public void setPresenter(ActivityViewListener presenter) {
        this.presenter = presenter;

    }

    @Override
    public void sendToActivityRecordList(Patient patient, Objective model, Activity activity) {

        this.aView.setPatientAndObjectiveAndActivity(patient, model, activity);

    }

    private void setupSaveButton() {
        saveButton.addClickListener(event -> this.presenter.save());

        this.addComponent(saveButton);
        this.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
    }
}
