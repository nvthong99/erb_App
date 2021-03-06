package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import api.ParkApi;
import beans.Bike;
import beans.Park;
import common.Constants;
import components.CRUDTable;
import controller.ParkController;

public class ParkPane extends JPanel {
	private static final long serialVersionUID = 1L;

	private CRUDTable<Park> table;
	private ParkApi parkApi;
	private BikeRental userBikeRental;
	private ParkController parkController;

	public ParkPane(CRUDTable<Bike> rentedBikeTable) {
		super();
		initialize(rentedBikeTable);
	}

	private void initialize(CRUDTable<Bike> rentedBikeTable) {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		LinkedHashMap<String, Action> events = new LinkedHashMap<>();
		events.put(Constants.PARK_READ, new ReadEvent());

		table = new CRUDTable<>(Park.getFields());
		table.initialize(events, null, new SearchEvent());

		parkApi = new ParkApi();
		table.updateData(parkApi.getAll());
		parkController = new ParkController(table, parkApi);

		userBikeRental = new BikeRental(rentedBikeTable);
		this.add(table, BorderLayout.CENTER);
	}

	private class ReadEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			Object bean = table.getSelectedBean();
			if (bean instanceof Park) {
				Park park = (Park) bean;
				userBikeRental.showDialog(park);
			}
		}
	}

	private class SearchEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = table.getSearchField().getText();
			if (text != null && text != "") {
				table.updateData(parkController.onSearch(text));
			}
		}
	}

}
