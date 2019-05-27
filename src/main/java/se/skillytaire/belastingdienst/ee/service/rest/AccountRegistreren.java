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

import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantResultTO;
import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantTO;
import se.skillytaire.belastingdienst.ee.service.account.RegistreerNieuwAccount;
import se.skillytaire.belastingdienst.ee.service.account.RegistreerNieuweKlant;
import se.skillytaire.belastingdienst.ee.service.account.UsernameCheck;

@Path("registreren")
public class AccountRegistreren {

	@Inject
	private UsernameCheck usernameCheck;
	@Inject
	private RegistreerNieuweKlant nieuweKlant;
	@Inject
	private RegistreerNieuwAccount nieuwAccount;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response Registreren(@FormParam("your_name") String username, @FormParam("password") String password,
			@FormParam("your_email") String email) {
		boolean isAangemaakt = false;
		if (usernameCheck.isBeschikbaar(username)) {
			NieuweKlantTO nieuweKlantTo = new NieuweKlantTO(username, password, email);
			NieuweKlantResultTO result = nieuweKlant.doIt(nieuweKlantTo);
			//mail(mdb) uitsturen alvorens account is geregistreerd.
			if (result.getResult().isPresent()) {
				nieuwAccount.registreer(result.getResult().get());
				isAangemaakt = true;
			}
		}

		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject obj = factory.createObjectBuilder().add("success", isAangemaakt).add("username", username)
				.add("password", password).add("email", email).build();

		Response response = Response.ok().entity(obj.toString()).type(MediaType.APPLICATION_JSON_TYPE).build();

		return response;
	}
}
