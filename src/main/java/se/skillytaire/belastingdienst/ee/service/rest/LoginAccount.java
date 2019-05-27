package se.skillytaire.belastingdienst.ee.service.rest;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.skillytaire.belastingdienst.ee.service.account.PasswordCheck;
import se.skillytaire.belastingdienst.ee.service.account.UsernameCheck;

@Path("account")
public class LoginAccount {
	
	@Inject
	private UsernameCheck usernameCheck;
	@Inject
	private PasswordCheck passwordCheck;

	@POST
	@Path("inloggen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inloggen(@FormParam("username") String username, @FormParam("password") String password) {
		boolean valide = false;
		if (!usernameCheck.isBeschikbaar(username)) {
			valide = passwordCheck.isValide(username, password);
		} 		
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject obj = factory.createObjectBuilder()
			.add("success", valide)
			.add("username", username)
			.add("password", password)
			.build();
		
		Response response = Response.ok()
			.entity(obj.toString())
			.type(MediaType.APPLICATION_JSON_TYPE)
			.build();
		

		return response;
	}

	
}