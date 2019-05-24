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

import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReservering;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringTO;

@Path("reserveren")
public class MaakNieuweReservering {

	@Inject
	private NieuweReservering nieuweReservering;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response Reserveren(@FormParam("your_username") String accountUsername,
			@FormParam("verhuurder_username") String verhuurderUsername, @FormParam("activiteit") Integer oid) {
		NieuweReserveringTO nieuweReserveringTo = new NieuweReserveringTO(accountUsername, verhuurderUsername, oid);
		nieuweReservering.reserveren(nieuweReserveringTo);

		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject obj = factory.createObjectBuilder().add("your_username", accountUsername)
				.add("verhuurder_username", verhuurderUsername).add("activiteit", oid).build();

		Response response = Response.ok().entity(obj.toString()).type(MediaType.APPLICATION_JSON_TYPE).build();

		return response;
	}
}
