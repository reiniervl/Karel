package se.skillytaire.belastingdienst.ee.service.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
	public void inloggen(
			@FormParam("username") String username, 
			@FormParam("password") String password, 
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws IOException, ServletException {
		boolean valide = false;
		if (!usernameCheck.isBeschikbaar(username)) {
			valide = passwordCheck.isValide(username, password);
		}
		if(valide) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect(request.getContextPath() + "/reserveer.html");
			// request.getSession().getServletContext().getRequestDispatcher("reserveer.html").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login.html");
			// request.getSession().getServletContext().getRequestDispatcher("login.html").forward(request, response);
		}
	}

	
}