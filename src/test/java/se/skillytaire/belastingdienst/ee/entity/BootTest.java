package se.skillytaire.belastingdienst.ee.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;
import se.skillytaire.java.datatype.PositiveInteger;

@RunWith(JLCRunner.class)
@JLC(value = Boot.class, asJUnit = true)
public class BootTest {
	@This
	private RivierTocht thisRivierTocht;
	@This
	private MeerTocht thisMeerTocht;

	@This
	private Verhuurder thisVerhuurder;

	@That
	private Verhuurder thatVerhuurder;

	@This
	private PositiveInteger thisNummer;

	@That
	private PositiveInteger thatNummer;

	@Test
	public void fullConstructorTest() {
		Boot volledigeConstructie = new Boot(this.thisVerhuurder, this.thisNummer);
		Verhuurder actualVerhuurder = volledigeConstructie.getEigenaar();
		int actualBootnummer = volledigeConstructie.getNummer();
		int value = this.thisNummer.intValue();
		Assert.assertEquals(value, actualBootnummer);
		Assert.assertEquals(this.thisVerhuurder, actualVerhuurder);
	}

	@Test
	public void equalsGelijkTest() {
		Boot boot1 = new Boot(this.thisVerhuurder, this.thisNummer);
		Boot boot2 = new Boot(this.thisVerhuurder, this.thisNummer);
		Assert.assertTrue(boot1.equals(boot2));
	}

	@Test
	public void equalsGelijkeRefTest() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		Assert.assertTrue(boot.equals(boot));
	}

	@Test
	public void equalsNullTest() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		Assert.assertFalse(boot.equals(null));
	}

	@Test
	public void equalsOngelijkTest() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		Boot boot2 = new Boot(this.thatVerhuurder, this.thatNummer);
		Assert.assertFalse(boot.equals(boot2));
	}

	@Test
	public void testHash() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		int notExpected = 604107971;
		int actual = boot.hashCode();
		Assert.assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void testHasNummer() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		int actual = boot.getNummer();
		Assert.assertTrue(boot.hasNummer(actual));
		Assert.assertFalse(boot.hasNummer(458));
	}

	@Test
	public void testAddBoot() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		boot.addTocht(this.thisMeerTocht);
		boot.addTocht(this.thisRivierTocht);
	}

	@Test
	public void testRemoveBoot() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		boot.addTocht(this.thisMeerTocht);
		boot.addTocht(this.thisRivierTocht);
		boot.removeTocht(this.thisMeerTocht);
		boot.removeTocht(this.thisRivierTocht);
	}
	
	@Test
	public void StartTocht() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		assertTrue(boot.isBeschikbaar());
		boot.start(thisMeerTocht);
		assertFalse(boot.isBeschikbaar());
	}
	
	@Test
	public void hasLaatsteTocht() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		boot.start(thisMeerTocht);
		assertTrue(boot.hasLaatsteTocht());
	}
	
	@Test
	public void isInspectieNodig() {
		Boot boot = new Boot(this.thisVerhuurder, this.thisNummer);
		assertTrue(boot.isBeschikbaar());
		boot.start(thisMeerTocht);
		assertFalse(boot.isInspectieNodig());
	}
	
	
		
}
