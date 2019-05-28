package se.skillytaire.belastingdienst.ee.service.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * LoguitAccount
 */
	@Path("uitloggen")
public class LoguitAccount {
	@GET
	public void uitloggen(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException,ServletException {
		String contextPath = request.getContextPath();
		request.getSession().getServletContext().getRequestDispatcher(contextPath + "inloggen.jsp").forward(request, response);
		request.getSession().invalidate();
	}
	
}