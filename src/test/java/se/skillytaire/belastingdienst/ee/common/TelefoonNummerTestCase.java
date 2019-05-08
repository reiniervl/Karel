package se.skillytaire.belastingdienst.ee.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.ImmutableObject;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = TelefoonNummer.class)
@ImmutableObject
public class TelefoonNummerTestCase {
   /*
    * It is reflexive: for any non-null reference value x, x.equals(x) should
    * return true.
    */
   @Test
   public void testEqualsReflective() {
      Object x = new TelefoonNummer(31, 123456789);
      boolean actual = x.equals(x);
      Assert.assertTrue(actual);
   }

   @Test
   public void test0031() {
      String landnr = "0031";
      int ln = Integer.parseInt(landnr);
      TelefoonNummer nr = new TelefoonNummer(ln, 683578);
      String expected = "+31 683578";
      Object actual = nr.toString();
      Assert.assertEquals(expected, actual);
   }
}
