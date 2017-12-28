package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Implements the GUI elements from the part Objective
 *
 * @author ziegm1
 */
public class ObjectiveViewImpl extends VerticalLayout implements ObjectiveView, View {
    /**
	 * 
	 */
	private static final long serialVersionUID = 298168717435855242L;

	private NavigationUI navUI;

    private ObjectiveViewListener presenter;
    private Label lblName = new Label();
    private final ActivityListViewImpl aView = new ActivityListViewImpl();

    private FormLayout form = new FormLayout();
    private TextField nameTextField = new TextField("Name");
    private Slider difficultySlider = new Slider("Difficulty");
    private TextArea descriptionArea = new TextArea("Description");

    private Button saveButton = new Button("Save");

    public ObjectiveViewImpl(NavigationUI navUI) {

        // the reference back to the navigation to communicate with the other
        // view components
        this.navUI = navUI;
        this.presenter = null;

        lblName.setStyleName("header");
        this.addStyleName("noPadding");

        this.addComponent(lblName);

        setupForm();

        setupSaveButton();

        this.addComponent(aView);
    }

    /**
     * building the form for the objective
     * with the fields Name, description etc.
     */
    private void setupForm() {

        form.setWidth(100.0f, Unit.PERCENTAGE);

        setupNameTextField();

        setupDescriptionArea();

        setupSlider();

        this.addComponent(form);
    }

    private void setupNameTextField() {
        nameTextField.setIcon(VaadinIcons.USER);
        nameTextField.setRequiredIndicatorVisible(true);
        nameTextField.setWidth(100.0f, Unit.PERCENTAGE);

        nameTextField.addValueChangeListener(event -> {
            this.presenter.setObjectiveName(event.getValue());
            this.lblName.setValue(event.getValue());

            validateForm();
        });

        form.addComponent(nameTextField);
    }

    private void setupDescriptionArea() {
        // the textarea for description
        descriptionArea.setIcon(VaadinIcons.COMMENT);
        descriptionArea.setRequiredIndicatorVisible(true);
        descriptionArea.setWidth(100.0f, Unit.PERCENTAGE);

        descriptionArea.addValueChangeListener(event -> this.presenter.setObjectiveDescription(event.getValue()));

        form.addComponent(descriptionArea);
    }


    private void setupSlider() {
        difficultySlider.setMin(1);
        difficultySlider.setMax(10);
        difficultySlider.setValue(5.0);
        difficultySlider.setIcon(VaadinIcons.HAMMER);
        difficultySlider.setWidth(100.0f, Unit.PERCENTAGE);
        form.addComponent(difficultySlider);

        difficultySlider.addValueChangeListener(event -> this.presenter.setObjectiveDifficulty(event.getValue().intValue()));
    }

    private void setupSaveButton() {

        saveButton.addClickListener(event -> this.presenter.save());

        this.addComponent(saveButton);
        this.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
    }

    // validates the form on empty and wrong occurences
    private void validateForm() {
        if (this.nameTextField.getValue().length() >= 1) {
            saveButton.setEnabled(true);
        }
        else {
            saveButton.setEnabled(false);
        }
    }

    /**
     * Adjusts the view for a new objective.
     * (renames button to 'add', disables aView (...)
     */
    private void setViewOnNewObjective() {
        this.saveButton.setCaption("Add");
        this.aView.setEnabled(false);
    }

    /**
     * Does the opposite of setViewOnExistingObjective()
     *
     */
    private void setViewOnExistingObjective() {
        this.saveButton.setCaption("Save");
        this.aView.setEnabled(true);
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.difficultySlider.setValue((double) difficulty);
    }

    @Override
    public void setIscomplete(String iscomplete) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setName(String name) {
        lblName.setValue(name);
        nameTextField.setValue(name);
    }

    /**
     *
     */
    @Override
    public void addedObjective() {
        setViewOnExistingObjective();

        navUI.getNavigator().navigateTo(NavigationUI.OBJECTIVEVIEW + "/" +
                this.presenter.getPatient().getPid() + "/" + this.presenter.getModel().getOid());

        Notification notification = new Notification("New Objective added!", "Successful");
        notification.setDelayMsec(1000);
        notification.show(navUI.getPage());
    }

    @Override
    public void setDescription(String description) {
        descriptionArea.setValue(description);
    }

    @Override
    public void enter(ViewChangeEvent event) {

        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            this.patientAndObjectiveNotFound();
        } else {

            String parameter = event.getParameters();


            String[] params = parameter.split("/");

            // todo this whole else if needs to be reviewed and rewritten.
            if (params.length != 2) {
                this.patientAndObjectiveNotFound();
            }
            // if a new objective is getting added
            else if (params[1].equals(NavigationUI.NEW)) {

                presenter.initNewObjective(Integer.parseInt(params[0]));
                setViewOnNewObjective();
            } else {
                int pid = -1;
                int oid = -1;

                try {
                    pid = Integer.parseInt(params[0]);
                    oid = Integer.parseInt(params[1]);
                } catch (NumberFormatException ex) {
                    this.patientAndObjectiveNotFound();
                }

                setViewOnExistingObjective();
                presenter.requestObjectiveWithPatientAndId(pid, oid);
            }
        }
    }

    @Override
    public void patientAndObjectiveNotFound() {
        this.navUI.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
    }

    @Override
    public void clearView() {
        lblName.setValue("");
        descriptionArea.setValue("");

        aView.clearView();
    }

    @Override
    public void setPresenter(ObjectiveViewListener presenter) {
        this.presenter = presenter;
    }
}
