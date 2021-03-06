package se.skillytaire.belastingdienst.ee.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.belastingdienst.ee.common.TelefoonNummer;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = EmbeddedTelefoonNummer.class)
public class EmbeddedTelefoonNummerTestCase {
   /*
    * It is reflexive: for any non-null reference value x, x.equals(x) should
    * return true.
    */
   @Test
   public void testEqualsReflective() {
      Object x = new EmbeddedTelefoonNummer(new TelefoonNummer( 31, 123456789));
      boolean actual = x.equals(x);
      Assert.assertTrue(actual);
   }

   @Test
   public void test0031() {
      String landnr = "0031";
      int ln = Integer.parseInt(landnr);
      EmbeddedTelefoonNummer nr = new EmbeddedTelefoonNummer(new TelefoonNummer( ln, 683578));
      String expected = "+31 683578";
      Object actual = nr.toString();
      Assert.assertEquals(expected, actual);
   }
}
