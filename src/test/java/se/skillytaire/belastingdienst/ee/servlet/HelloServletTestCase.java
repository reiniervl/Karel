// package se.skillytaire.belastingdienst.ee.servlet;
//
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;
//
// import org.apache.commons.httpclient.HttpClient;
// import org.apache.commons.httpclient.HttpStatus;
// import org.apache.commons.httpclient.methods.GetMethod;
// import org.junit.BeforeClass;
// import org.junit.Test;
// import org.junit.runner.RunWith;
//
// import se.skillytaire.course.tools.jlc.JLC;
// import se.skillytaire.course.tools.jlc.JLCRunner;
// import se.skillytaire.course.tools.jlc.Property;
// import se.skillytaire.course.tools.jlc.TestPropertyFile;
//
// @RunWith(JLCRunner.class)
// @JLC(asJUnit = true)
// @TestPropertyFile
// public class HelloServletTestCase {
// @Property(key = "app.url.http")
// private static String URL;
//
// @Test
// public void testServlet() throws Exception {
// System.out.println(URL);
// HttpClient client = new HttpClient();
//
// GetMethod method = new GetMethod(URL);
//
// try {
// int statusCode = client.executeMethod(method);
//
// assertEquals("HTTP GET failed", HttpStatus.SC_OK, statusCode);
//
// String response = method.getResponseBodyAsString(1000);
//
// assertTrue("Unexpected response body", response.contains("Hello! How are you
// today?"));
// } finally {
// method.releaseConnection();
// }
// }
// }
