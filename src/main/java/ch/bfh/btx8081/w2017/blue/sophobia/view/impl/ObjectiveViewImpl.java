package ch.bfh.btx8081.w2017.blue.sophobia.view.impl;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2017.blue.sophobia.model.Objective;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ActivityListPresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.presenter.ObjectivePresenter;
import ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces.ObjectiveView;

/**
 * Implements the GUI elements from the part Objective
 * @author ziegm1
 *
 */
public class ObjectiveViewImpl extends VerticalLayout implements ObjectiveView {

	private Label lblName = new Label();
	ActivityListViewImpl aView = new ActivityListViewImpl();
	final VerticalLayout layout = new VerticalLayout();
	private ObjectivePresenter objectivePresenter;
	
	public ObjectiveViewImpl() {
		
		this.setStyleName("noPadding");
		
		lblName.setStyleName("header");
		
		layout.addComponent(lblName);
		
		this.addComponent(layout);
	}

	@Override
	public void setOid(int oid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDifficulty(int difficulty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIscomplete(String iscomplete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name) {
		lblName.setValue(name);
	}
	
	@Override
	public void setPresenter(ObjectivePresenter presenter) {
		this.objectivePresenter = presenter;
	}
}
