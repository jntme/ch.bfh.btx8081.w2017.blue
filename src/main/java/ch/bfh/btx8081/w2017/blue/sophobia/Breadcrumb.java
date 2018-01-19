package ch.bfh.btx8081.w2017.blue.sophobia;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Activity;
import ch.bfh.btx8081.w2017.blue.sophobia.model.ActivityRecord;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.model.Patient;

/**
 * First version of the breadcrumb navigation. To implement or fix: - refresh
 * buttons on change, - design, - change to MVP
 * 
 * @author gfels6
 */
public class Breadcrumb extends HorizontalLayout {

	private static final long serialVersionUID = -8202299071305818976L;

	private Button btnHome;
	private Button btnPatient;
	private Button btnObjective;
	private Button btnActivity;
	private Button btnActivityRecord;
	private Patient pat;
	private Objective obj;
	private Activity act;
	private ActivityRecord actRec;
	private NavigationUI navUI = null;

	public Breadcrumb(NavigationUI navUI) {

		this.navUI = navUI;

		setup();

		this.addComponent(btnHome);
		this.addComponent(btnPatient);
		this.addComponent(btnObjective);
		this.addComponent(btnActivity);
		this.addComponent(btnActivityRecord);

	}

	/**
	 * Initializes the buttons and add listener for home button
	 */
	private void setup() {

		btnHome = new Button("Patientenselektion");
		btnHome.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		btnPatient = new Button("");
		btnPatient.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		btnObjective = new Button("");
		btnObjective.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		btnActivity = new Button("");
		btnActivity.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		btnActivityRecord = new Button("");
		btnActivityRecord.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		this.btnHome.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -6917620301709928610L;

			@Override
			public void buttonClick(ClickEvent event) {
				navUI.getNavigator().navigateTo(NavigationUI.SELECTPATIENTVIEW);
				Page.getCurrent().reload();
			}

		});

	}

	/**
	 * set patient, fill button and add clicklistener with ids
	 * 
	 * @param patient
	 *            model
	 */
	public void setPatLoc(Patient patient) {
		this.pat = patient;
		btnPatient.setVisible(true);
		btnPatient.setCaption(pat.getPrename() + " " + pat.getName());

		this.btnPatient.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3677358383383685965L;

			@Override
			public void buttonClick(ClickEvent event) {
				navUI.getNavigator().navigateTo(NavigationUI.PATIENTVIEW + "/" + pat.getPid());
			}

		});
	}

	/**
	 * set objective, fill button and add clicklistener with ids
	 * 
	 * @param objective
	 *             model
	 */
	public void setObjLoc(Objective objective) {
		this.obj = objective;
		btnObjective.setVisible(true);
		btnObjective.setCaption(objective.getName());

		this.btnObjective.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3250419890761153579L;

			@Override
			public void buttonClick(ClickEvent event) {
				navUI.getNavigator().navigateTo(NavigationUI.OBJECTIVEVIEW + "/" + pat.getPid() + "/" + obj.getOid());
			}

		});

	}

	/**
	 * set activity, fill button and add clicklistener with ids
	 * 
	 * @param activity
	 *             model
	 */
	public void setActLoc(Activity activity) {
		this.act = activity;
		btnActivity.setVisible(true);
		btnActivity.setCaption(act.getName());

		this.btnActivity.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 3793009732195360573L;

			@Override
			public void buttonClick(ClickEvent event) {
				navUI.getNavigator().navigateTo(
						NavigationUI.ACTIVITYVIEW + "/" + pat.getPid() + "/" + obj.getOid() + "/" + act.getAid());
			}

		});
	}

	/**
	 * set activtyRecord, fill button and add clicklistener with ids
	 * 
	 * @param actRecord
	 *             model
	 */
	public void setActRecLoc(ActivityRecord actRecord) {
		this.actRec = actRecord;
		btnActivityRecord.setVisible(true);
		btnActivityRecord.setCaption("Eintrag");

		this.btnActivityRecord.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -6333125749316667053L;

			@Override
			public void buttonClick(ClickEvent event) {
				navUI.getNavigator().navigateTo(NavigationUI.ACTIVITYRECORDVIEW + "/" + pat.getPid() + "/"
						+ obj.getOid() + "/" + act.getAid() + "/" + actRec.getArId());
			}

		});
	}

	public void clearBtn() {

		btnPatient.setVisible(false);
		btnObjective.setVisible(false);
		btnActivity.setVisible(false);
		btnActivityRecord.setVisible(false);

	}

}
