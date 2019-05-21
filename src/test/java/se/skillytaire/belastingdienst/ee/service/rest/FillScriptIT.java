package se.skillytaire.belastingdienst.ee.service.rest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.Property;
import se.skillytaire.course.tools.jlc.TestPropertyFile;


@RunWith(JLCRunner.class)
@JLC(asJUnit = true)
@TestPropertyFile
public class FillScriptIT extends EndpointHelper {

   private static final String BINDING=
      new StringBuilder()
         .append('/')
         .append(AccountApplication.PATH)         
         .append('/')
         .append(Fill.ROOT_PATH)
         .append('/')
         .append(Fill.FILL_GET_PATH)
         .toString();
   @Ignore
   @Test
   public void testConnection() {
      testEndpoint(BINDING, "true");
   }
}
