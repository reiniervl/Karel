package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.KlantBuilder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.NieuwAccountResultTO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class RegistreerNieuwAccountTest {
	@This Klant klant;
	@This Account account;

	EntityManagerFactory factory;
	EntityManager em;

	RegistreerNieuwAccountEJB beanUnderTest;


	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		factory = Persistence.createEntityManagerFactory("stuga");
		em = factory.createEntityManager();
		AccountJpaDAO.getInstance().setEntityManager(em);
		KlantJpaDAO.getInstance().setEntityManager(em);
		beanUnderTest = new RegistreerNieuwAccountEJB();
		beanUnderTest.accountDAO = AccountJpaDAO.getInstance();
		beanUnderTest.klantDAO = KlantJpaDAO.getInstance();
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
	public void testRegistreerAccount() {
		Klant k = KlantBuilder.builder()
			.withPassword("password")
			.withUsername("username")
			.withEmail("email@createment.nl")
			.build();
		
		beanUnderTest.klantDAO.add(k);
		assertTrue(k.isPersistant());
		assertFalse("Persisting account", account.isPersistant());
		NieuwAccountResultTO result = beanUnderTest.registreer(k.getOid());
		assertTrue("Persisted account", result.isSuccessful());
		assertTrue(result.getResult().isPresent());
		assertEquals(NieuwAccountResultTO.NO_ERROR, result.getCode());
	}

	@Test
	public void testRegistreerAccountNonpersistentKlant() {
		Klant k = KlantBuilder.builder()
			.withPassword("password")
			.withUsername("username")
			.withEmail("email@createment.nl")
			.build();
		
		// beanUnderTest.klantDAO.add(k);
		assertFalse(k.isPersistant());
		assertFalse("Persisting account", account.isPersistant());
		NieuwAccountResultTO result = beanUnderTest.registreer(new Integer(1));
		assertFalse("Persisted account", result.isSuccessful());

	}
}