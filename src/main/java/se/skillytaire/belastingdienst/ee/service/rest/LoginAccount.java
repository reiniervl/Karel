package se.skillytaire.belastingdienst.ee.service.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

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
			response.sendRedirect(request.getContextPath() + "/reserveren.jsp");
			// request.getSession().getServletContext().getRequestDispatcher("reserveer.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/inloggen.jsp");
			// request.getSession().getServletContext().getRequestDispatcher("login.html").forward(request, response);
		}
	}

	@GET
	@Path("uitloggen")
	public void uitloggen(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException,ServletException {
		String contextPath = request.getContextPath();
		request.getSession().invalidate();
		response.sendRedirect(contextPath + "/inloggen.jsp");
	}
}