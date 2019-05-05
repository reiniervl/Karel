package se.skillytaire.belastingdienst.ee.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = Reservering.class, asJUnit = true)
public class ReserveringTest {
   int reserveringsNummer = 555555555;

   @Test
   public void fullConstructorTest() {
      Reservering volledigeReservering = new Reservering(
            this.reserveringsNummer);
      int actual = volledigeReservering.getReserveringsNummer();
      Assert.assertEquals(this.reserveringsNummer, actual);
   }

   @Test
   public void equalsGelijkTest() {
      Reservering reservering1 = new Reservering(this.reserveringsNummer);
      int hetzelfde = 555555555;
      Reservering reservering2 = new Reservering(hetzelfde);
      Assert.assertTrue(reservering1.equals(reservering2));
   }

   @Test
   public void equalsGelijkeRefTest() {
      Reservering reservering1 = new Reservering(this.reserveringsNummer);
      Assert.assertTrue(reservering1.equals(reservering1));
   }

   @Test
   public void equalsNullTest() {
      Reservering reservering1 = new Reservering(this.reserveringsNummer);
      Assert.assertFalse(reservering1.equals(null));
   }

   @Test
   public void equalsOngelijkTest() {
      Reservering reservering1 = new Reservering(this.reserveringsNummer);
      int anders = 666666666;
      Reservering reservering2 = new Reservering(anders);
      Assert.assertFalse(reservering1.equals(reservering2));
   }

   @Test
   public void testHash() {
      Reservering reservering1 = new Reservering(this.reserveringsNummer);
      int notExpected = 604107971;
      int actual = reservering1.hashCode();
      Assert.assertNotEquals(notExpected, actual);
   }

   // @Test
   // public void testGetVerloopDatum() {
   // Reservering reservering1 = new Reservering(this.reserveringsNummer);
   // reservering1.setVerloopDatum(LocalDateTime.now());
   // Assert.assertEquals(reservering1.getVerloopDatum(), LocalDateTime.now());
   // }

   // @Test
   // public void testGetReserveringDatum() {
   // Reservering reservering1 = new Reservering(this.reserveringsNummer);
   // reservering1.setReserveringsDatum(LocalDateTime.now());
   // Assert.assertEquals(reservering1.getReserveringsDatum(),
   // LocalDateTime.now());
   // }

   // @Test
   // public void testGetReserveringNummer() {
   // Reservering reservering1 = new Reservering(this.reserveringsNummer);
   // int actual = reservering1.getReserveringsNummer();
   // Assert.assertEquals(this.reserveringsNummer, actual);
   // }

}
