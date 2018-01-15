package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordView;

/**
 * Activity Records can be displayed, changed and created with this view.
 *
 * @author kybup1
 */
public class ActivityRecordViewImpl extends VerticalLayout implements ActivityRecordView, View {

    private static final long serialVersionUID = 1L;

    private ActivityRecordViewListener presenter;

    private NavigationUI navUi;

    private DateField txdDate = new DateField("Erstellt am:");
    private TextArea txaDescription = new TextArea("Beschreibung");
    private Slider sldSuccess = new Slider("Erfolg");
    private FormLayout form = new FormLayout();

    private Button btnSave = new Button(VaadinIcons.CHECK);

    public ActivityRecordViewImpl(NavigationUI navUi) {
        this.navUi = navUi;
        this.presenter = null;

        setupForm();
        setupSaveButton();

    }

    /**
     * Initializes the form to edit the activity record data
     */
    private void setupForm() {

        form.setWidth(100.0f, Unit.PERCENTAGE);

        txdDate.setIcon(VaadinIcons.CALENDAR);
        txdDate.setRequiredIndicatorVisible(true);
        txdDate.setEnabled(false);
        txdDate.setWidth(100.0f, Unit.PERCENTAGE);

        form.addComponent(txdDate);

        txaDescription.setIcon(VaadinIcons.COMMENT);
        txaDescription.setRequiredIndicatorVisible(true);
        txaDescription.setWidth(100.0f, Unit.PERCENTAGE);

        form.addComponent(txaDescription);

        sldSuccess.setMin(1);
        sldSuccess.setMax(10);
        sldSuccess.setValue(5.0);
        sldSuccess.setIcon(VaadinIcons.HAMMER);
        sldSuccess.setWidth(100.0f, Unit.PERCENTAGE);
        form.addComponent(sldSuccess);

        this.addComponent(form);
    }

    /**
     * Sets up the save button and adds a click listener to it.
     */
    private void setupSaveButton() {
        btnSave.addClickListener(event -> this.presenter.save());

        this.addComponent(btnSave);
        this.setComponentAlignment(btnSave, Alignment.MIDDLE_RIGHT);
    }

    @Override
    public Date getDate() {
        return Date.from(txdDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void setDate(Date date) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        txdDate.setValue(localDate);
    }

    @Override
    public String getDescription() {
        return txaDescription.getValue();
    }

    @Override
    public void setDescription(String description) {
        txaDescription.setValue(description);
    }

    @Override
    public int getSuccess() {
        return sldSuccess.getValue().intValue();
    }

    @Override
    public void setSuccess(int success) {
        sldSuccess.setValue((double) success);
    }

    @Override
    public void setNewActivityRecord() {
        txdDate.setEnabled(true);
        //btnSave.setCaption("Hinzufügen");

    }

    /**
     * Clears the fields in the form and resets the view to the original state.
     */
    @Override
    public void clearView() {
        txdDate.clear();
        txaDescription.setValue("");
        sldSuccess.setValue(5.0);
        btnSave.setCaption("");
        txdDate.setEnabled(false);

    }

    /**
     * Should be called if the url has errors
     */
    private void urlParseError() {
        this.navUi.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
    }

    @Override
    public void addListener(ActivityRecordViewListener listener) {
        this.presenter = listener;
    }

    /**
     * Parses the url and saves the different IDs in corresponding variables. The IDs are than forwarded to the presenter.
     * The presenter than resolves them into the corresponding objects.
     */
    @Override
    public void enter(ViewChangeEvent event) {
        int pid = -1;
        int oid = -1;
        int aid = -1;
        int arid = -1;

        if (event.getParameters() == null || event.getParameters().isEmpty()) {
            this.urlParseError();
            ;
        } else {
            String url = event.getParameters();
            String[] params = url.split("/");

            // todo this whole else if needs to be reviewed and rewritten.
            if (params.length != 4) {
                this.urlParseError();
            } else if (params[3].equals(navUi.NEW)) {
                try {
                    pid = Integer.parseInt(params[0]);
                    oid = Integer.parseInt(params[1]);
                    aid = Integer.parseInt(params[2]);
                } catch (NumberFormatException ex) {
                    this.urlParseError();
                }
                setNewActivityRecord();
            } else {
                try {
                    pid = Integer.parseInt(params[0]);
                    oid = Integer.parseInt(params[1]);
                    aid = Integer.parseInt(params[2]);
                    arid = Integer.parseInt(params[3]);
                } catch (NumberFormatException ex) {
                    this.urlParseError();
                }
            }
        }
        presenter.resolveIds(pid, oid, aid, arid);
    }

    /**
     * Changes the form after a new Activity Record is created and saved.
     */
    @Override
    public void changeToExistingActRec(int arid) {
        navUi.getNavigator().navigateTo(NavigationUI.ACTIVITYRECORDVIEW + getUrl() + arid);
        txdDate.setEnabled(false);
        btnSave.setCaption("");
    }

    /**
     * Retrieves the url and trims it.
     *
     * @return part of url: /pid/oid/aid/
     */
    private String getUrl() {
        String url = navUi.getPage().getUriFragment();
        return url.substring(navUi.ACTIVITYRECORDVIEW.length() + 1, url.length() - 3);
    }

}
