package controller;

import javax.swing.JOptionPane;

import api.BikeApi;
import beans.Bike;
import components.CRUDTable;
import interfaces.AController;

public class BikeController extends AController<Bike> {

	public BikeController(CRUDTable<Bike> table, BikeApi api) {
		super(table, api);
	}

	public void onRent(Bike bike) {
		try {
			((BikeApi) api).rent(bike);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Rent error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void onGiveBack(Bike bike) {
		try {
			((BikeApi) api).giveBack(bike);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Rent error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
