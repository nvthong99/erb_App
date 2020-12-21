package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import api.ParkApi;
import api.UserApi;
import beans.Park;
import beans.User;
import common.Constants;
import components.CRUDTable;
import controller.ParkController;
import controller.UserController;
import dialog.Dialog;



public class ParkManager extends JPanel {
	private static final long serialVersionUID = 1L;
	private CRUDTable<Park> table;
	private ParkController parkController;
	private Dialog<Park> updateDialog;
	private Dialog<Park> createDialog;
	
	public ParkManager() {
		super();
		initialize();
	}
	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		ArrayList<Park> data = ParkApi.getAllParks();
		ParkController parkController = new ParkController();
		CRUDTable<Park> table = new CRUDTable<>( Park.getFields());
//		System.out.println(data);

		ArrayList<String> names = new ArrayList<>();
		names.add(Constants.UPDATE);
		names.add(Constants.DELETE);

		ArrayList<Action> events = new ArrayList<>();
		events.add(new UpdateEvent());
		events.add(new DeleteEvent());

		table = new CRUDTable<>(User.getFields());
		table.initialize(names, events, new CreateEvent());

		parkController = new ParkController();
		table.updateData(ParkApi.getAllParks());

		updateDialog = new Dialog<>(User.getUpdateFields());
		updateDialog.initialize("Cập nhật bãi xe", "Cập nhật");

		createDialog = new Dialog<>(User.getCreateFields());
		createDialog.initialize("Thêm bãi xe", "Thêm");
		
		this.add(table, BorderLayout.CENTER);
	}
	

	private class UpdateEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Object bean = table.getSelectedBean();
			System.out.println(bean);
			if (bean instanceof Park) {
				updateDialog.updateDate((Park) bean);
			}
			updateDialog.setModal(true);
			updateDialog.setVisible(true);
		}
	}

	private class DeleteEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Object bean = table.getSelectedBean();
			System.out.println(bean);
		}
	}

	private class CreateEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			createDialog.setModal(true);
			createDialog.setVisible(true);
		}
	}
	
}
