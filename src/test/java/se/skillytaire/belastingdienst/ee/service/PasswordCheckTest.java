package se.skillytaire.belastingdienst.ee.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.KlantBuilder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.ejb.PasswordCheckEJB;
import se.skillytaire.course.tools.jlc.JLCRunner;

public class PasswordCheckTest {
	private PasswordCheckEJB beanUnderTest;
	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		this.factory = Persistence.createEntityManagerFactory("stuga");
		this.entityManager = this.factory.createEntityManager();
		KlantJpaDAO.getInstance().setEntityManager(this.entityManager);
		beanUnderTest = new PasswordCheckEJB(KlantJpaDAO.getInstance());
	}

	@After
	public void destroyJPA() {
		if (this.entityManager != null) {
			this.entityManager.close();
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

	private void addWithTX(final Klant klant) {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		try {
			unmanagedTx.begin();
			KlantJpaDAO.getInstance().add(klant);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}

	@Test
	public void isNietValideTest() {
		Klant klant = KlantBuilder.builder().withPassword("123").withUsername("Karel").withEmail("sexyboy@gmail.com")
				.build();
		KlantJpaDAO.getInstance().add(klant);
		this.addWithTX(klant);
		Assert.assertFalse(beanUnderTest.isValide("Karel", "SexyBoy"));
	}
	
	@Test
	public void isValideTest() {
		Klant klant = KlantBuilder.builder().withPassword("123").withUsername("Karel").withEmail("sexyboy@gmail.com")
				.build();
		KlantJpaDAO.getInstance().add(klant);
		this.addWithTX(klant);
		Assert.assertTrue(beanUnderTest.isValide("Karel", "123"));
	}
	
	@Test
	public void passwordCheck() {
		PasswordCheckEJB checker = new PasswordCheckEJB();
		checker.getClass();
	}
}
