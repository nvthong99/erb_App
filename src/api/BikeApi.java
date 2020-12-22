package api;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Bike;
import common.Constants;
import helpers.ResponseCustom;
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
		// TODO Auto-generated method stub

		WebTarget webTarget = Constants.client.target(Constants.PATH).path("bikes").path("rent").path(bike.getId());

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));

		ResponseCustom<Bike> res = response.readEntity(ResponseCustom.class);

	}

	public void giveBack(Bike bike, String parkId) {
		WebTarget webTarget = Constants.client.target(Constants.PATH).path("bikes").path("return").path(bike.getId());

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));

		ResponseCustom<Bike> res = response.readEntity(ResponseCustom.class);

	}

	public ArrayList<Bike> getRentedBikeAll() {
		try {
			WebTarget webTarget = Constants.client.target(Constants.PATH).path("bikes").path("usingByUser").path("1");

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

}