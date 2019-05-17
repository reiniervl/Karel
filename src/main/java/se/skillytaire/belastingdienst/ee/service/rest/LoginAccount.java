package se.skillytaire.belastingdienst.ee.service.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("account")
public class LoginAccount {
// username:String
// password:String

// return: text/plain

	@POST
	@Path("inloggen")
	@Produces(MediaType.TEXT_PLAIN)
	public String inloggen(@FormParam("username") String username, @FormParam("password") String password) {
		

		return String.format("Username: %s\tPassword: %s", username, password);
	}

	
}