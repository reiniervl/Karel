package se.skillytaire.belastingdienst.ee.service.rest;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.skillytaire.belastingdienst.ee.service.account.UsernameCheck;


@Path("checkusername")
public class CheckUsername {

	@Inject
	UsernameCheck usernameCheck;

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkUsername(@PathParam("username") String username) {
		boolean beschikbaar = usernameCheck.isBeschikbaar(username);
		JsonObject job = Json.createObjectBuilder()
			.add("success", true)
			.add("beschikbaar", beschikbaar)
			.build();

		return Response.ok(job).build();
	}
}