package se.skillytaire.belastingdienst.ee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NieuweKlantTOTest {
	@Test
	public void testEmptyKlantTO() {
		NieuweKlantResultTO result = new NieuweKlantResultTO();
		assertEquals(NieuweKlantResultTO.NIEUWE_KLANT_EXISTS, result.getCode());
	}
	@Test
	public void testNieuweKlantTO() {
		NieuweKlantTO klantTO = new NieuweKlantTO("username", "password", "email@createment.nl");
		assertTrue("Username exists", klantTO.getUsername() != null);
		assertTrue("Password exists", klantTO.getPassword() != null);
		assertTrue("Email exists", klantTO.getEmail() != null);
		assertTrue(klantTO.toString() != null);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testNoUsername() {
		NieuweKlantTO klantTO = new NieuweKlantTO(null, "password", "email@createment.nl");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testNoPassword() {
		NieuweKlantTO klantTO = new NieuweKlantTO("username", null, "email@createment.nl");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testNoEmail() {
		NieuweKlantTO klantTO = new NieuweKlantTO("username", "password", null);
	}
}