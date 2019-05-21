package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Reservering.class, asJUnit = false)
public class ReserveringTest {
	@This
	private Account thisAccount;
	@That
	private Account thatAccount;
	@This
	private MeerTocht thisMeerTocht;
	@That
	private RivierTocht thatRivierTocht;

	private static final LocalDateTime TIJD = LocalDateTime.now();
	
	@Test
	public void fullConstructorTest() {
		Reservering volledigeReservering = new Reservering(thisAccount, thisMeerTocht);
		Account actualAccount = volledigeReservering.getAccount();
		Assert.assertEquals(this.thisAccount, actualAccount);
	}

	@Test
	public void equalsGelijkTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Reservering reservering2 = new Reservering(thisAccount, thisMeerTocht);
		reservering1.setReserveringsDatum(TIJD);
		reservering2.setReserveringsDatum(TIJD);
		Assert.assertTrue(reservering1.equals(reservering2));
	}

	@Test
	public void equalsGelijkeRefTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Assert.assertTrue(reservering1.equals(reservering1));
	}

	@Test
	public void equalsNullTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Assert.assertFalse(reservering1.equals(null));
	}

	@Test
	public void equalsOngelijkTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Reservering reservering2 = new Reservering(thatAccount, thisMeerTocht);
		Reservering reservering3 = new Reservering(thatAccount, thatRivierTocht);
		Assert.assertFalse(reservering1.equals(reservering2));
		Assert.assertFalse(reservering1.equals(reservering3));
	}

	@Test
	public void testHash() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		int notExpected = 604107971;
		int actual = reservering1.hashCode();
		Assert.assertNotEquals(notExpected, actual);
	}

	@Test
	public void testAddTocht() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Assert.assertTrue(reservering1.add(thatRivierTocht));
	}

	@Test
	public void testRemoveTocht() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		reservering1.add(thatRivierTocht);
		Assert.assertTrue(reservering1.remove(thatRivierTocht));
	}
}
