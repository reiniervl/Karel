package se.skillytaire.belastingdienst.ee.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Boeking.class, asJUnit = true)
public class BoekingTest {

	@This
	private Account thisAccount;
	@That
	private Account thatAccount;
	@This
	private MeerTocht thisMeerTocht;
	@That
	private RivierTocht thatRivierTocht;
	
	@Test
	public void fullConstructorTest() {
		Reservering volledigeReservering = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking = new Boeking(volledigeReservering);
		Reservering testReservering = volledigeBoeking.getReservering();
		Assert.assertEquals(volledigeReservering, testReservering);
	}
	
	@Test
	public void fullConstructorTest2() {
		Reservering volledigeReservering = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking = new Boeking(volledigeReservering);
		Boeking volledigeBoeking2 = new Boeking(volledigeBoeking);
		Reservering testReservering = volledigeBoeking.getReservering();
		Assert.assertEquals(volledigeReservering, testReservering);
		Assert.assertEquals(volledigeBoeking, volledigeBoeking2);
	}
	
	@Test
	public void equalsGelijkTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking1 = new Boeking(reservering1);
		Reservering reservering2 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking2 = new Boeking(reservering2);
		Assert.assertEquals(volledigeBoeking1, volledigeBoeking2);
	}

	@Test
	public void equalsGelijkeRefTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking = new Boeking(reservering1);
		Assert.assertTrue(volledigeBoeking.equals(volledigeBoeking));
	}

	@Test
	public void equalsNullTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking = new Boeking(reservering1);
		Assert.assertFalse(volledigeBoeking.equals(null));
	}

	@Test
	public void equalsOngelijkTest() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking1 = new Boeking(reservering1);
		Reservering reservering2 = new Reservering(thatAccount, thisMeerTocht);
		Boeking volledigeBoeking2 = new Boeking(reservering2);
		Reservering reservering3 = new Reservering(thatAccount, thatRivierTocht);
		Boeking volledigeBoeking3 = new Boeking(reservering3);
		Assert.assertFalse(volledigeBoeking1.equals(volledigeBoeking2));
		Assert.assertFalse(volledigeBoeking1.equals(volledigeBoeking3));
	}

	@Test
	public void testHash() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking1 = new Boeking(reservering1);
		int notExpected = 604107971;
		int actual = volledigeBoeking1.hashCode();
		Assert.assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void testGetReservering() {
		Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
		Boeking volledigeBoeking1 = new Boeking(reservering1);
		assertTrue(reservering1.equals(volledigeBoeking1.getReservering()));
	}
}
