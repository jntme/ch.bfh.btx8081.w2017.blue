package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.util.Iterator;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ObjectiveList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.PatientObjectiveListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.PatientObjectiveListView;

/**
 * Implements the GUI elements from the part PatientObjectiveList
 *
 * @author ziegm1, jntme
 */
public class PatientObjectiveListViewImpl extends Panel implements PatientObjectiveListView {

    private static final long serialVersionUID = 84052613008053572L;

    private Patient patient = null;

    private final Label DISPLAY = new Label("Ziele");
    private Button btnDelete;
    private Button btnAdd;

    private PatientObjectiveListViewListener presenter = null;
    private Grid<Objective> grid = new Grid<>();

    /**
     * Constructor creates a new grid for viewing the object list as a table and adds some formatting.
     */
    public PatientObjectiveListViewImpl(NavigationUI navUI) {
        VerticalLayout vLayout = new VerticalLayout();
        HorizontalLayout hLayout1 = new HorizontalLayout();
        HorizontalLayout hLayout2 = new HorizontalLayout();

        vLayout.addComponent(hLayout1);
        vLayout.addComponent(hLayout2);

        btnAdd = new Button(VaadinIcons.PLUS_CIRCLE);
        btnDelete = new Button(VaadinIcons.TRASH);

        hLayout1.addComponents(DISPLAY, btnAdd, btnDelete);

        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.setWidth(100.0f, Unit.PERCENTAGE);

        hLayout2.setWidth(100.0f, Unit.PERCENTAGE);
        hLayout2.addComponent(grid);
        grid.addColumn(Objective::getName).setCaption("Ziel");
        grid.addColumn(Objective::getDifficulty).setCaption("Schwierigkeit");
        grid.addColumn(Objective::getDescription).setCaption("Beschreibung");

        grid.addItemClickListener(new ItemClickListener<Objective>() {
            private static final long serialVersionUID = 2068314108919135281L;

            public void itemClick(ItemClick<Objective> event) {
                if (event.getMouseEventDetails().isDoubleClick()) {
                    System.out.println(NavigationUI.OBJECTIVEVIEW + "/" + patient.getPid() + "/" + event.getItem().getOid());
                    navUI.getNavigator().navigateTo(NavigationUI.OBJECTIVEVIEW + "/" + patient.getPid() + "/" + event.getItem().getOid());
                }
            }
        });

        btnAdd.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 8269978402173622033L;

            @Override
            public void buttonClick(ClickEvent event) {
                navUI.getNavigator().navigateTo(NavigationUI.OBJECTIVEVIEW + "/" + patient.getPid() + "/" + NavigationUI.NEW);
            }
        });

        btnDelete.addClickListener(event -> {
            grid.getSelectedItems().forEach(objective -> this.presenter.deleteObjective(objective));
            grid.getSelectedItems().forEach(objective -> grid.getSelectedItems().remove(objective));
            grid.clearSortOrder();
            Notification notification = new Notification("Erolgreich gelöscht!", "Löschen");
            notification.setDelayMsec(1000);
            notification.show(navUI.getPage());
        });


        this.setContent(vLayout);
    }

    /**
     * Adds all the items of an object list to the grid
     */
    @Override
    public void fillObjectiveList(ObjectiveList objectiveList) {
        grid.setItems(objectiveList.getObjectives());
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        fillObjectiveList(patient.getObjectiveList());
    }

    /**
     * Returns the objectives which are currently selected in the view
     *
     * @return currently selected objectives
     */
    @Override
    public Objective getSelectedObjective() {
        Iterator<Objective> itr = grid.getSelectedItems().iterator();
        return itr.next();
    }

    /**
     * Listener for event handling
     */
    @Override
    public void addListener(PatientObjectiveListViewListener presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPresenter(PatientObjectiveListPresenter presenter) {
    }

    @Override
    public void clearView() {
        patient = null;
        Objective emptyList = new Objective();
        grid.setItems(emptyList);
    }
}