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
import javax.ws.rs.core.Context;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReservering;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringTO;

@Path("reserveren")
public class MaakNieuweReservering {

	@Inject
	private NieuweReservering nieuweReservering;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void Reserveren(
			@FormParam("verhuurder") String verhuurderUsername,
			@FormParam("tocht") Integer oid,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException, ServletException {
		String accountUsername = (String) request.getSession().getAttribute("username");
		NieuweReserveringTO nieuweReserveringTo = new NieuweReserveringTO(accountUsername, verhuurderUsername, oid);
		nieuweReservering.reserveren(nieuweReserveringTo);

	}
}
