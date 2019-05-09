package se.skillytaire.belastingdienst.ee.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = RivierTocht.class, asJUnit = true)
public class RivierTochtTest {
   @This
   private Periode periode1;
   private double prijs1 = 16D;
   @That
   private Periode periode2;
   private double prijs2 = 200D;

   @Test
   public void fullConstructorTest() {
      RivierTocht volledigeReservering = new RivierTocht(prijs1, periode1);
      double actual = volledigeReservering.getPrijs();
      Periode actualPeriode = volledigeReservering.getReserveringsPeriode();
      Assert.assertEquals(prijs1, actual, 0);
      Assert.assertEquals(periode1, actualPeriode);
   }

   @Test
   public void equalsGelijkTest() {
      RivierTocht rivierTocht1 = new RivierTocht(prijs1, periode1);
      RivierTocht rivierTocht2 = new RivierTocht(prijs1, periode1);
      Assert.assertTrue(rivierTocht1.equals(rivierTocht2));
   }

   @Test
   public void equalsGelijkeRefTest() {
      RivierTocht rivierTocht = new RivierTocht(prijs1, periode1);
      Assert.assertTrue(rivierTocht.equals(rivierTocht));
   }

   @Test
   public void equalsNullTest() {
      RivierTocht rivierTocht = new RivierTocht(prijs1, periode1);
      Assert.assertFalse(rivierTocht.equals(null));
   }

   @Test
   public void equalsOngelijkTest() {
      RivierTocht rivierTocht1 = new RivierTocht(prijs1, periode1);
      RivierTocht rivierTocht2 = new RivierTocht(prijs2, periode2);
      Assert.assertFalse(rivierTocht1.equals(rivierTocht2));
   }

   @Test
   public void testHash() {
      RivierTocht rivierTocht1 = new RivierTocht(prijs1, periode1);
      int notExpected = 604107971;
      int actual = rivierTocht1.hashCode();
      Assert.assertNotEquals(notExpected, actual);
   }
}
