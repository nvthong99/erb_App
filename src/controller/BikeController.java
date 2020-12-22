package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import api.BikeApi;
import beans.Bike;
import beans.Park;
import components.CRUDTable;
import interfaces.AController;

public class BikeController extends AController<Bike> {
	private CRUDTable<Bike> rentedBike;

	public BikeController(CRUDTable<Bike> table, BikeApi api) {
		super(table, api);
	}

	public BikeController(CRUDTable<Bike> table, BikeApi api, CRUDTable<Bike> rentedBike) {
		super(table, api);
		this.rentedBike = rentedBike;
	}

	public void onRent(Bike bike) {
		int isDelete = JOptionPane.showConfirmDialog(null,
				"Việc này không thể hoàn tác. Bạn có chắc chắn muốn xóa không?!", "Xóa", JOptionPane.YES_NO_OPTION);
		boolean isOk = false;
		if (isDelete == JOptionPane.YES_OPTION) {
			try {
				((BikeApi) api).rent(bike);
				table.updateData(api.getAll());
				rentedBike.updateData(((BikeApi) api).getRentedBikeAll());
			} catch (Exception error) {
				error.printStackTrace();
				JOptionPane.showMessageDialog(null, "Create error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void onGiveBack(Bike bike, ArrayList<Park> parks) {

		ArrayList<String> values = new ArrayList<>();
		for (Park park : parks) {
			values.add(park.getId());
		}

		String parkId = (String) JOptionPane.showInputDialog(null, "Chose park to give back", "Selection",
				JOptionPane.DEFAULT_OPTION, null, values.toArray(), "0");

		if (parkId != null) {
			try {
				((BikeApi) api).giveBack(bike, parkId);
				table.updateData(api.getAll());
				rentedBike.updateData(((BikeApi) api).getRentedBikeAll());
			} catch (Exception error) {
				error.printStackTrace();
				JOptionPane.showMessageDialog(null, "Give back error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
