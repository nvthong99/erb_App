package api;

import java.util.ArrayList;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Bike;
import common.Constants;
import interfaces.IApi;

public class BikeApi implements IApi<Bike> {

	private String parkId;

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public ArrayList<Bike> getAll() {
		try {
			WebTarget webTarget = Constants.client.target(Constants.PATH).path("bikes").path("fromPark").path(parkId);

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			ArrayList<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {
			});

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public void rent(Bike bike) {
		
	}

}