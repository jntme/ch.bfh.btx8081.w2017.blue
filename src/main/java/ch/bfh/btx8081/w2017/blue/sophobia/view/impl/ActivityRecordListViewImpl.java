package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecordList;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ActivityRecordListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ActivityRecordListView;

/**
 * @author ziegm
 */
public class ActivityRecordListViewImpl extends Panel implements ActivityRecordListView, ClickListener {
	private static final long serialVersionUID = -3140144466857083444L;

	private final Label header = new Label("Verlaufsdokumentation");
	private Button bAdd = new Button(VaadinIcons.PLUS_CIRCLE);
	private Button bDelete = new Button(VaadinIcons.TRASH);

	private ActivityRecordListViewListener presenter = null;
	private Grid<ActivityRecord> grid = new Grid<>();
	
	public ActivityRecordListViewImpl(NavigationUI navUI) {
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout1 = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();

		vLayout.addComponents(hLayout1, hLayout2);

		hLayout1.addComponents(header, bAdd, bDelete);

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setWidth(100.0f, Unit.PERCENTAGE);

		hLayout2.setWidth(100.0f, Unit.PERCENTAGE);
		hLayout2.addComponent(grid);
		
		// format date for usage in the date column of the grid.
		ValueProvider<ActivityRecord, String> activityRecordDate = (activityRecord) -> {
			Date date = activityRecord.getDate();
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
			return df.format(date);
		};

		grid.addColumn(activityRecordDate).setCaption("Datum");
		grid.addColumn(ActivityRecord::getSuccess).setCaption("Erfolg");
		
		grid.addItemClickListener(new ItemClickListener<ActivityRecord>() {
			private static final long serialVersionUID = -9107765887521817876L;

			public void itemClick(ItemClick<ActivityRecord> event) {
//				if (event.getMouseEventDetails().isDoubleClick()) {
//					navUI.getNavigator().navigateTo(NavigationUI.ACTIVITYRECORDVIEW + "/" + patient.getPid() + "/" + objective.getOid() + "/" + activity.getAid() + "/" + event.getItem().getArId());
//				}
			}
		});
		
		bDelete.addClickListener(event -> {
            grid.getSelectedItems().forEach(activityRecord -> this.presenter.deleteActivityRecord(activityRecord));
            grid.getSelectedItems().forEach(activityRecord -> grid.getSelectedItems().remove(activityRecord));
            grid.clearSortOrder();
            Notification notification = new Notification("Deleted successful.", "deletion");
            notification.setDelayMsec(1000);
            notification.show(navUI.getPage());
        });
		
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
	public void addListener(ActivityRecordListViewListener presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setPresenter(ActivityRecordListPresenter presenter) {
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
	}

	public void setPatientAndObjectiveAndActivity(Patient patient, Objective model, Activity activity) {
	}
}
