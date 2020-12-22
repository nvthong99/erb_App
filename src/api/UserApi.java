package api;

import java.util.ArrayList;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import common.Constants;
import helpers.ResponseCustom;
import interfaces.IApi;

public class UserApi implements IApi<User> {

	public User getUser() {

		WebTarget webTarget = Constants.client.target(Constants.PATH).path("users").path("1");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ResponseCustom<User> res = response.readEntity(ResponseCustom.class);
		System.out.println("fdsfsd" + res.getT());
		if (res.getStatus() == 1) {
			return res.getT();
		}
		return null;
	}

	@Override
	public ArrayList<User> getAll() {
		try {
			WebTarget webTarget = Constants.client.target(Constants.PATH).path("users");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			ArrayList<User> res = response.readEntity(new GenericType<ArrayList<User>>() {
			});

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public ArrayList<User> getAll(String text) {
		return null;
	}

}