package se.skillytaire.belastingdienst.ee.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Test;

public class PeriodeTest {

   @Test
   public void testPeriode() {
      Periode periode = new Periode();
      assertEquals(Periode.class, periode.getClass());
   }

   @Test
   public void testPeriodeZoneId() {
      Periode periode = new Periode();

      assertEquals(ZoneId.systemDefault(), periode.getZoneId());
   }

   @Test
   public void testStart() {
      Periode periode = new Periode();

      assertFalse(periode.getStart() != null);

      periode.start();

      assertTrue(periode.getStart() != null);
   }

   @Test(expected=RuntimeException.class)
   public void testStartNull() {
      Periode periode = new Periode();
      periode.start();
      periode.start();
   }


   @Test
   public void testNotStart() {
      Periode periode = new Periode();

      assertFalse(periode.getStart() != null);
   }

   @Test
   public void testBeeindig() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();

      assertTrue(periode.getEind() != null);
   }

   @Test(expected=RuntimeException.class)
   public void testBeeindigNull() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();
      periode.beeindig();
   }

   @Test
   public void testIsGestart() {
      Periode periode = new Periode();
      periode.start();

      assertTrue(periode.isGestart());
   }

   @Test
   public void testIsBeeindigd() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();

      assertTrue(periode.isBeeindigd());
   }

   @Test
   public void testGetDuur() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();

      assertTrue(periode.getDuur() != null);
   }

   @Test(expected=RuntimeException.class)
   public void testGetDuurPanicExclimationMark() {
      Periode periode = new Periode();
      periode.getDuur();
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

      assertTrue(periode1.compareTo(periode2) > 0);
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

      assertTrue(periode1.compareTo(periode2) < 0);
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

      assertTrue(periode1.compareTo(periode2) > 0);
   }

   @Test
   public void testToString() {
      Periode periode = new Periode();
      assertTrue(periode.toString() instanceof String);
   }

   @Test
   public void testGetStart() {
      Periode periode = new Periode();
      periode.start();

      assertTrue(periode.getStart() != null);
   }

   @Test
   public void testSetStart() {
      Periode periode = new Periode();
      periode.start();
      LocalDateTime ldt = LocalDateTime.now();
      periode.setStart(ldt);

      assertEquals(ldt, periode.getStart());
   }

   @Test
   public void testGetEind() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();

      assertTrue(periode.getEind() != null);
   }

   @Test
   public void testSetEind() {
      Periode periode = new Periode();
      periode.start();
      periode.beeindig();
      LocalDateTime ldt = LocalDateTime.now();
      periode.setEind(ldt);

      assertEquals(ldt, periode.getEind());
   }

   @Test
   public void testGetZoneId() {
      Periode periode = new Periode();

      assertEquals(ZoneId.systemDefault(), periode.getZoneId());
   }

   @Test
   public void testGetZoneIdWithZone() {
      ZoneId ny = ZoneId.of("America/New_York");
      Periode periode = new Periode(ny);

      assertEquals(ny, periode.getZoneId());
   }

   @Test
   public void testSetZoneId() {
      Periode periode = new Periode();
      ZoneId ny = ZoneId.of("America/New_York");
      periode.setZoneId(ny);

      assertEquals(ny, periode.getZoneId());
   }

}
