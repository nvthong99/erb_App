package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import api.ParkApi;
import beans.Park;
import common.Constants;
import components.CRUDTable;

public class ParkPane extends JPanel {
	private static final long serialVersionUID = 1L;

	private CRUDTable<Park> table;
	private ParkApi parkApi;
	private BikeRental userBikeRental;

	public ParkPane() {
		super();
		initialize();
	}

	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		LinkedHashMap<String, Action> events = new LinkedHashMap<>();
		events.put(Constants.PARK_READ, new ReadEvent());

		table = new CRUDTable<>(Park.getFields());
		table.initialize(events, null);

		parkApi = new ParkApi();
		table.updateData(parkApi.getAll());

		userBikeRental = new BikeRental();
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

}
