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
	@This
	private Account thisAccount;
	@That
	private Account thatAccount;
	@This
	private MeerTocht thisRivierTocht;
	@That
	private RivierTocht thatRivierTocht;

	@Test
	public void fullConstructorTest() {
		Reservering volledigeReservering = new Reservering(thisAccount, thisRivierTocht);
//		int actual = volledigeReservering.getReserveringsNummer();
		Account actualAccount = volledigeReservering.getAccount();
//		Assert.assertEquals(volledigeReservering.getOid(), actual);
		Assert.assertEquals(this.thisAccount, actualAccount);
	}

	@Test
	public void equalsGelijkTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		Reservering reservering2 = new Reservering(thisAccount, thisRivierTocht);
		reservering1.setReserveringsDatum(ReserveringTestFactory.TIJD);
		reservering2.setReserveringsDatum(ReserveringTestFactory.TIJD);
		Assert.assertTrue(reservering1.equals(reservering2));
	}

	@Test
	public void equalsGelijkeRefTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		Assert.assertTrue(reservering1.equals(reservering1));
	}

	@Test
	public void equalsNullTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		Assert.assertFalse(reservering1.equals(null));
	}

	@Test
	public void equalsOngelijkTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		Reservering reservering2 = new Reservering(thatAccount, thisRivierTocht);
		Reservering reservering3 = new Reservering(thatAccount, thatRivierTocht);
		Assert.assertFalse(reservering1.equals(reservering2));
		Assert.assertFalse(reservering1.equals(reservering3));
	}

	@Test
	public void testHash() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		int notExpected = 604107971;
		int actual = reservering1.hashCode();
		Assert.assertNotEquals(notExpected, actual);
	}

	@Test
	public void testAddTocht() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		Assert.assertTrue(reservering1.add(thatRivierTocht));
	}

	@Test
	public void testRemoveTocht() {
		Reservering reservering1 = new Reservering(thisAccount, thisRivierTocht);
		reservering1.add(thatRivierTocht);
		Assert.assertTrue(reservering1.remove(thatRivierTocht));
	}
}
