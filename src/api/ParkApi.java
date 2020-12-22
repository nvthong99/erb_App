package api;

import java.util.ArrayList;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Bike;
import beans.Park;
import common.Constants;
import interfaces.IApi;

public class ParkApi implements IApi<Park> {

	@Override
	public ArrayList<Park> getAll(String text) {
		try {
			WebTarget webTarget = Constants.client.target(Constants.PATH).path("parks").queryParam("search", text);

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			ArrayList<Park> res = response.readEntity(new GenericType<ArrayList<Park>>() {
			});

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static ArrayList<Park> getAllPark() {
		try {
			WebTarget webTarget = Constants.client.target(Constants.PATH).path("parks");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			ArrayList<Park> res = response.readEntity(new GenericType<ArrayList<Park>>() {
			});

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public ArrayList<Park> getAll() {
		try {
			WebTarget webTarget = Constants.client.target(Constants.PATH).path("parks");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			ArrayList<Park> res = response.readEntity(new GenericType<ArrayList<Park>>() {
			});

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}