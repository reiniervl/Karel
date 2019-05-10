package se.skillytaire.belastingdienst.ee.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Reservering.class, asJUnit = true)
public class ReserveringTest {
	int reserveringsNummer = 555555555;
	@This
	private Klant thisKlant;
	@That
	private Klant thatKlant;
	@This
	private MeerTocht eenTocht;

	@Test
	public void fullConstructorTest() {
		Reservering volledigeReservering = new Reservering(this.reserveringsNummer, thisKlant);
		int actual = volledigeReservering.getReserveringsNummer();
		Klant actualKlant = volledigeReservering.getKlant();
		Assert.assertEquals(this.reserveringsNummer, actual);
		Assert.assertEquals(this.thisKlant, actualKlant);
	}

	@Test
	public void equalsGelijkTest() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		int hetzelfde = 555555555;
		Reservering reservering2 = new Reservering(hetzelfde, thisKlant);
		Assert.assertTrue(reservering1.equals(reservering2));
	}

	@Test
	public void equalsGelijkeRefTest() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		Assert.assertTrue(reservering1.equals(reservering1));
	}

	@Test
	public void equalsNullTest() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		Assert.assertFalse(reservering1.equals(null));
	}

	@Test
	public void equalsOngelijkTest() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		int anders = 666666666;
		Reservering reservering2 = new Reservering(anders, thatKlant);
		Assert.assertFalse(reservering1.equals(reservering2));
	}

	@Test
	public void testHash() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		int notExpected = 604107971;
		int actual = reservering1.hashCode();
		Assert.assertNotEquals(notExpected, actual);
	}

	@Test
	public void testAddTocht() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		Assert.assertTrue(reservering1.add(eenTocht));
	}

	@Test
	public void testRemoveTocht() {
		Reservering reservering1 = new Reservering(this.reserveringsNummer, thisKlant);
		reservering1.add(eenTocht);
		Assert.assertTrue(reservering1.remove(eenTocht));
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
