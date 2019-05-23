package se.skillytaire.belastingdienst.ee.service.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;

@Path("activiteiten")
public class BeschikbareTochten {
	
	@Path("meertocht/{verhuurder}")
	@GET
	public Response beschikbareMeerTochten (@PathParam("verhuurder") String verhuurder) {
		return Response.ok().build();
	}
	
	@Path("riviertocht/{verhuurder}")
	@GET
	public Response beschikbareRivierTochten (@PathParam("verhuurder") String verhuurder) {
		return Response.ok().build();
	}
	
}
