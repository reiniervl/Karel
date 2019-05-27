package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.jpa.EntityManagerTestRule;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantResultTO;
import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantTO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class RegistreerNieuweKlantTest {
	@Rule
	public EntityManagerTestRule jpa = EntityManagerTestRule.persistenceUnit("stuga");
	@This Klant klant;

	RegistreerNieuweKlantEJB beanUnderTest;
	private KlantJpaDAO beanUnderTest2;


	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		beanUnderTest2 = new KlantJpaDAO();
	    beanUnderTest2.setEntityManager(jpa.em());
		beanUnderTest = new RegistreerNieuweKlantEJB();
		beanUnderTest.dao = beanUnderTest2;
	}

	@Test
	public void testRegistreerKlant() {
		NieuweKlantTO klantTO = new NieuweKlantTO("username", "password", "email@createment.nl");
		NieuweKlantResultTO result = beanUnderTest.doIt(klantTO);
		assertTrue("Klant geregistreerd", result.isSuccessful());
	}

	// @Test
	// public void testRegistreerKlantTwice() {
	// 	NieuweKlantTO klantTO = new NieuweKlantTO("username", "password", "email@createment.nl");
	// 	NieuweKlantResultTO result1 = beanUnderTest.doIt(klantTO);
	// 	assertTrue("Klant geregistreerd", result1.isSuccessful());
	// 	NieuweKlantResultTO result2 = beanUnderTest.doIt(klantTO);
	// 	assertFalse("Klant geregistreerd", result2.isSuccessful());
	// }
}