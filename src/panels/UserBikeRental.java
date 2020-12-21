package panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

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
	
	private CRUDTable<Bike> table;
	private BikeController bikeController;
	
	private BikeApi bikeApi;
	private BikeManager bikeManager;

	
	public UserBikeRental() {
		super();
		
	}
	
	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		LinkedHashMap<String, Action> events = new LinkedHashMap<>();
		events.put(Constants.READ, new ReadEvent());
//		events.put(Constants.UPDATE, new UpdateEvent());
//		events.put(Constants.DELETE, new DeleteEvent());

		table = new CRUDTable<>(Bike.getFields());
//		table.initialize(events, new CreateEvent());

		bikeApi = new BikeApi() ;
		bikeController = new BikeController(table, bikeApi);
		System.out.println(bikeApi.getAll());
		table.updateData(bikeApi.getAll());

		

		bikeManager = new BikeManager();

		this.add(table, BorderLayout.CENTER);
	}
	
	private class ReadEvent extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			Object bean = table.getSelectedBean();
			if (bean instanceof Park) {
				Bike bike = (Bike) bean;
				bikeManager.showDialog(bike.getId());
			}
		}
	}
}
