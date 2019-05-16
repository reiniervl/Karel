package se.skillytaire.belastingdienst.ee.service.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

@Path("usernamecheck")
public class UsernameCheck {

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public StreamingOutput checkUsername(@PathParam("username") String username) {
		boolean exists = false;
		StringBuilder sb = new StringBuilder();
		sb.append("{\n")
			.append("\t\"success\"")
			.append(" : ")
			.append("true")
			.append(",\n")
			.append("\t\"exists\"")
			.append(" : ")
			.append(exists)
			.append("\n}");
		
			return new StreamingOutput(){
			
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException {
					PrintWriter p = new PrintWriter(output);
					p.write(sb.toString());
					p.flush();
				}
			};
	}
}