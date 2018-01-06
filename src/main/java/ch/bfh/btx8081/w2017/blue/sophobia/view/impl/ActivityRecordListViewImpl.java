package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.NavigationUI;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordListView;

/**
 * @author ziegm
 *
 */
public class ActivityRecordListViewImpl extends Panel implements ActivityRecordListView, ClickListener {
	private static final long serialVersionUID = -3140144466857083444L;

	private NavigationUI navUI = null;

	private final Label header = new Label("Verlaufsdokumentation");
	private Button bAdd = new Button(VaadinIcons.PLUS_CIRCLE);
	private Button bDelete = new Button(VaadinIcons.TRASH);

	private List<ActivityRecordListViewListener> listeners = new ArrayList<>();
	private Grid<ActivityRecord> grid = new Grid<>();

	private Patient patient = null;
	private Objective objective = null;
	private Activity activity = null;

	public ActivityRecordListViewImpl(NavigationUI navUI) {
		this.navUI = navUI;

		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout1 = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();

		vLayout.addComponents(hLayout1, hLayout2);

		hLayout1.addComponents(header, bAdd, bDelete);

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setWidth(100.0f, Unit.PERCENTAGE);

		hLayout2.setWidth(100.0f, Unit.PERCENTAGE);
		hLayout2.addComponent(grid);
		
		ValueProvider<ActivityRecord, String> activityRecordDate = (activityRecord) -> {
			Date date = activityRecord.getDate();
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
			return df.format(date);
		};

		grid.addColumn(activityRecordDate).setCaption("Datum");
		grid.addColumn(ActivityRecord::getSuccess).setCaption("Erfolg");

		// Vorbereitung für Phil
//		grid.addItemClickListener(new ItemClickListener<ActivityRecord>() {
//			private static final long serialVersionUID = -9107765887521817876L;
//
//			public void itemClick(ItemClick<ActivityRecord> event) {
//				if (event.getMouseEventDetails().isDoubleClick()) {
//					navUI.getNavigator().navigateTo(NavigationUI.ACTIVITYRECORDVIEW + "/" + patient.getPid() + "/" + objective.getOid() + "/" + activity.getAid() + "/" + event.getItem().getArId());
//				}
//			}
//		});

		this.setContent(vLayout);

	}

	@Override
	public void fillActivityRecordList(ActivityRecordList activityRecordList) {
		grid.setItems(activityRecordList.getActivityRecord());

	}

	@Override
	public ActivityRecord getSelectedActivityRecord() {
		Iterator<ActivityRecord> itr = grid.getSelectedItems().iterator();
		return itr.next();
	}

	@Override
	public void clearView() {
		this.grid.setItems(new ArrayList<>());
	}

	@Override
	public void addListener(ActivityRecordListViewListener listener) {
		listeners.add(listener);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		for (ActivityRecordListViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption().charAt(0));
		}

	}

	@Override
	public void setIsEnabled(boolean isEnabled) {
		this.setEnabled(isEnabled);
	}

	public void setPatientAndObjectiveAndActivity(Patient patient, Objective model, Activity activity) {
		this.patient = patient;
		this.objective = model;
		this.activity = activity;
	}

}
