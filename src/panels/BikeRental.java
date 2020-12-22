package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import api.BikeApi;
import beans.Bike;
import beans.Park;
import common.Constants;
import components.CRUDTable;
import controller.BikeController;

public class BikeRental extends JDialog {
	private static final long serialVersionUID = 1L;

	private CRUDTable<Bike> table;
	private BikeController bikeController;
	private BikeApi bikeApi;

	public BikeRental() {
		super();
		initialize();
	}

	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setSize(Constants.DIALOG_WIDTH, Constants.DIALOG_HEIGHT);
		this.setLayout(layout);

		LinkedHashMap<String, Action> events = new LinkedHashMap<>();
		events.put(Constants.RENTAL, new RentalEvent());

		table = new CRUDTable<>(Bike.getFields());
		table.initialize(events, null);

		bikeApi = new BikeApi();
		bikeController = new BikeController(table, bikeApi);

		this.add(table, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
	}

	public void showDialog(Park park) {
		bikeApi.setParkId(park.getId());
		table.updateData(bikeApi.getAll());
		this.setModal(true);
		this.setVisible(true);
	}

	private class RentalEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Object bean = table.getSelectedBean();

			if (bean instanceof Bike) {
				Bike bike = (Bike) bean;
				bikeController.onRent(bike);
			}
		}
	}

}
