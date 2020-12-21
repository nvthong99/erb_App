package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.BikeApi;
import api.ParkApi;
import beans.Bike;
import beans.Park;
import common.Constants;
import components.CRUDTable;
import components.OptionPane;
import controller.BikeController;
import controller.ParkController;



public class UserBikeRental extends JPanel{
	private static final long serialVersionUID = 1L;

	private CRUDTable<Bike> table;
	private BikeController bikeController;
	private OptionPane<Bike> cardNumberDialog;

	private BikeApi bikeApi;


	
	public UserBikeRental() {
		super();
		initialize();
		
	}
	
	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		LinkedHashMap<String, Action> events = new LinkedHashMap<>();
		events.put(Constants.RENTAL, new RentalEvent());

		table = new CRUDTable<>(Bike.getFields());
		table.initialize(events, null);

		bikeApi = new BikeApi();
		bikeController = new BikeController(table, bikeApi);
		table.updateData(bikeApi.getAlls());

		cardNumberDialog = new OptionPane<>(Bike.getUpdateFields());
		cardNumberDialog.initialize("Cập nhật xe", "Cập nhật");



		this.add(table, BorderLayout.CENTER);
	}

	private class RentalEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Object bean = table.getSelectedBean();

			if (bean instanceof Bike) {
				Bike bike = (Bike) bean;
				cardNumberDialog.updateDate(bike);
			}

			LinkedHashMap<String, String> result = cardNumberDialog.showDialog();
			if (result != null) {
				ObjectMapper mapper = new ObjectMapper();
				Bike park = mapper.convertValue(result, Bike.class);
				bikeController.onUpdate(park);
			}
		}
	}

}
