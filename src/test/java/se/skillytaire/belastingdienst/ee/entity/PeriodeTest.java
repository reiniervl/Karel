package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Periode;

//@RunWith(JLCRunner.class)
//@JLC(value = Periode.class, asJUnit = true)
public class PeriodeTest {

   @Test
   public void testPeriode() {
      Periode periode = new Periode();
      Assert.assertEquals(Periode.class, periode.getClass());
   }

   @Test
   public void testStart() {
      Periode periode = new Periode();
      Assert.assertFalse(periode.isGestart());

      periode.start();
      Assert.assertTrue(periode.isGestart());
   }

   @Test
   public void testNotStart() {
      Periode periode = new Periode();
      Assert.assertFalse(periode.isGestart());
   }

   @Test
   public void testBeeindig() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();
      Assert.assertTrue(periode.isBeeindigd());
   }

   @Test
   public void testduurimmutable() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();
      Optional<Duration> duur1 = periode.getDuur();
      Assert.assertNotNull(duur1);
      Assert.assertTrue(duur1.isPresent());
      Duration deechteduur1 = duur1.get();

      periode.beeindig();
      Optional<Duration> duur2 = periode.getDuur();
      Assert.assertNotNull(duur2);
      Assert.assertTrue(duur2.isPresent());
      Duration deechteduur2 = duur2.get();

      Assert.assertEquals(deechteduur1, deechteduur2);
   }

   @Test
   public void testIsGestart() {
      Periode periode = new Periode();
      periode.start();

      Assert.assertTrue(periode.isGestart());
   }

   @Test
   public void testIsBeeindigd() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();

      Assert.assertTrue(periode.isBeeindigd());
   }

   @Test
   public void testGetDuur() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();

      Assert.assertTrue(periode.getDuur() != null);
   }

   @Test
   public void testCompareTo() {
      Periode periode1 = new Periode();
      Periode periode2 = new Periode();
      periode1.start();
      periode2.start();
      periode2.beeindig();

      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      periode1.beeindig();

      Assert.assertTrue(periode1.compareTo(periode2) > 0);
   }

   @Test
   public void testCompareTo1NotStarted() {
      Periode periode1 = new Periode();
      Periode periode2 = new Periode();
      periode2.start();
      periode2.beeindig();

      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      periode1.beeindig();

      Assert.assertTrue(periode1.compareTo(periode2) < 0);
   }

   @Test
   public void testCompareTo2NotStarted() {
      Periode periode1 = new Periode();
      Periode periode2 = new Periode();
      periode1.start();

      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      periode1.beeindig();

      Assert.assertTrue(periode1.compareTo(periode2) > 0);
   }

   @Test
   public void testToString() {
      Periode periode = new Periode();
      Assert.assertTrue(periode.toString() instanceof String);
   }
}
