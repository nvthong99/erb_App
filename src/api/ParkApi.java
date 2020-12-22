package api;

import java.util.ArrayList;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Park;
import common.Constants;
import interfaces.IApi;

public class ParkApi implements IApi<Park> {

	public static ArrayList<Park> getAllPark() {
		WebTarget webTarget = Constants.client.target(Constants.PATH).path("parks");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Park> res = response.readEntity(new GenericType<ArrayList<Park>>() {
		});

		return res;
	}
	
	@Override
	public ArrayList<Park> getAll() {
		WebTarget webTarget = Constants.client.target(Constants.PATH).path("parks");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Park> res = response.readEntity(new GenericType<ArrayList<Park>>() {
		});

		return res;
	}

}