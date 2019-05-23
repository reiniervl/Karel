package se.skillytaire.belastingdienst.ee.service.rest;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Tocht;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochten;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochtenResultTO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochtenTO;

@Path("activiteiten")
public class BeschikbareTochten {
	
	@Inject
	BeschikbareMeerTochten beschikbareMeerTochten;

	@Path("meertocht/{verhuurder}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response beschikbareMeerTochten (@PathParam("verhuurder") String verhuurder) {
		Response response;
		BeschikbareMeerTochtenTO requestTO = new BeschikbareMeerTochtenTO(verhuurder);
		BeschikbareMeerTochtenResultTO resultTO = beschikbareMeerTochten.geefTochten(requestTO);
		JsonObjectBuilder job = Json.createObjectBuilder();

		if(resultTO.isSuccessful() && resultTO.getResult().isPresent()) {
			List<MeerTocht> tochten = resultTO.getResult().get();
			job.add("success", true)
				.add("tochten", parseMeerTochten(tochten));

			response = Response.ok(job.build()).build();
		} else {
			job.add("success", false);
			response = Response.ok(job.build()).build();
		}

		return response;
	}
	
	@Path("riviertocht/{verhuurder}")
	@GET
	public Response beschikbareRivierTochten (@PathParam("verhuurder") String verhuurder) {
		return Response.ok().build();
	}

	private JsonObjectBuilder parseMeerTochten(List<? extends Tocht<?>> tochten) {
		JsonObjectBuilder tochtenObj = Json.createObjectBuilder();
		for (Tocht<?> t : tochten) {
				tochtenObj.add("id", t.getOid())
					.add("prijs", t.getPrijs())
					.add("start", t.getReserveringsPeriode().getStart().toString())
					.add("eind", t.getReserveringsPeriode().getEind().toString());
			}
		return tochtenObj;
	}
	
}
