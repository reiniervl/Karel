package se.skillytaire.belastingdienst.ee.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Account.class, asJUnit = true)
public class AccountTest {
	@This Reservering reservering;
	@This Account account;

	@Test
	public void testAddReservering() {
		assertTrue(this.account.add(this.reservering));
	}

	@Test
	public void testRemoveReservering() {
		assertTrue(this.account.add(this.reservering));
		assertTrue(this.account.remove(this.reservering));
	}
}