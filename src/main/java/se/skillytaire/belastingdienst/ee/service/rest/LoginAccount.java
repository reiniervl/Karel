package se.skillytaire.belastingdienst.ee.service.rest;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("account")
public class LoginAccount {
// username:String
// password:String

// return: text/plain

	@POST
	@Path("inloggen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inloggen(@FormParam("username") String username, @FormParam("password") String password) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject obj = factory.createObjectBuilder()
			.add("success", false)
			.add("reason", "username could not be found")
			.add("code", 0)
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