package se.skillytaire.belastingdienst.ee.service.rest;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skillytaire.course.tools.jlc.Property;

public class EndpointHelper {
   private static final Logger log = LoggerFactory.getLogger(EndpointHelper.class);
   @Property(key = "app.url.http")
   private static String URL;
   
   public void testEndpoint(String endpoint, String expectedOutput) {
//      String port = System.getProperty("liberty.test.port");
//      String war = System.getProperty("war.context");
//      String apploc = System.getProperty("appLocation");
//      System.out
//            .println("Port: " + port + "\nWar: " + war + "\nApploc: " + apploc);
//      String url = "http://localhost:" + port + "/" + war + endpoint;
//      System.out.println("Testing " + url);
      
      String httpEndPoint = new StringBuilder(URL).append(endpoint).toString();
      log.debug("Connection to URL "+ httpEndPoint);
      
      Response response = sendRequest(httpEndPoint, "GET");
      int responseCode = response.getStatus();
      String msg;
      switch (responseCode) {
      case 404: msg = "Page Not Found, or Server Not Found";
         break;
      case 500: msg = "Internal Server Error";
         break;         

      default:msg = "Oeps!";
         break;
      }
      assertTrue(String.format("Not OK, response code %d %s  from %s", responseCode, msg,httpEndPoint),
            responseCode == 200);

      String responseString = response.readEntity(String.class);
      response.close();
      assertTrue("Incorrect response, response is " + responseString,
            responseString.contains(expectedOutput));
   }

   public Response sendRequest(String url, String requestType) {
      Client client = ClientBuilder.newClient();
      System.out.println("Testing " + url);
      WebTarget target = client.target(url);
      Invocation.Builder invoBuild = target.request();
      Response response = invoBuild.build(requestType).invoke();
      return response;
   }
}
