package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import api.BikeApi;
import beans.Bike;
import common.Constants;
import components.CRUDTable;
import controller.BikeController;

public class RentedBikePane extends JPanel {
	private static final long serialVersionUID = 1L;

	private CRUDTable<Bike> table;
	private BikeController bikeController;
	private BikeApi bikeApi;

	public RentedBikePane() {
		super();
		initialize();
	}

	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		LinkedHashMap<String, Action> events = new LinkedHashMap<>();
		events.put(Constants.GIVE_BACK, new GiveBackEvent());

		table = new CRUDTable<>(Bike.getFields());
		table.initialize(events, null);

		bikeApi = new BikeApi();
		bikeController = new BikeController(table, bikeApi);
		table.update(bikeApi.getRentedBikeAll());
	}

	private class GiveBackEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Object bean = table.getSelectedBean();

			if (bean instanceof Bike) {
				Bike bike = (Bike) bean;
				bikeController.onGiveBack(bike);
			}
		}
	}

}
