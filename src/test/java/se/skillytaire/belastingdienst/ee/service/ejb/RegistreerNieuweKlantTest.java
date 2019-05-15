package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.NieuweKlantResultTO;
import se.skillytaire.belastingdienst.ee.service.NieuweKlantTO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class RegistreerNieuweKlantTest {
	@This Klant klant;

	EntityManagerFactory factory;
	EntityManager em;

	RegistreerNieuweKlantEJB beanUnderTest;


	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		factory = Persistence.createEntityManagerFactory("stuga");
		em = factory.createEntityManager();
		KlantJpaDAO.getInstance().setEntityManager(em);
		beanUnderTest = new RegistreerNieuweKlantEJB();
		beanUnderTest.dao = KlantJpaDAO.getInstance();
	}

	@After
	public void destroyJPA() {
		if (this.em != null) {
			this.em.close();
		}
		if (this.factory != null) {
			this.factory.close();
			while (this.factory.isOpen() && !Thread.interrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
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