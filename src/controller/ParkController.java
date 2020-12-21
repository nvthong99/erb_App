package controller;

import api.ParkApi;
import beans.Park;
import components.CRUDTable;
import interfaces.AController;

public class ParkController extends AController<Park> {

	public ParkController(CRUDTable<Park> table, ParkApi api) {
		super(table, api);
	}

}
