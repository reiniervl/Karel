package se.skillytaire.belastingdienst.ee.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtJpaDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;

public class MeerTochtCheckTest {
	private MeerTochtCheck beanUnderTest;
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
		MeerTochtJpaDAO.getInstance().setEntityManager(this.entityManager);
		beanUnderTest = new MeerTochtCheck(MeerTochtJpaDAO.getInstance());
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

	public void addWithTX(final MeerTocht MeerTocht) {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		try {
			unmanagedTx.begin();
			MeerTochtJpaDAO.getInstance().add(MeerTocht);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}

	@Test
	@Ignore
	//FIXME parameters fixen
	public void isBeschikbaarTest() {
		Assert.assertTrue(beanUnderTest.isBeschikbaar(null, 12D, null));
	}

//	@Test
//	public void isNietBeschikbaarTest() {
//		Klant klant = KlantBuilder.builder().withPassword("123").withUsername("Karel").withEmail("sexyboy@gmail.com")
//				.build();
//		KlantJpaDAO.getInstance().add(klant);
//		this.addWithTX(klant);
//		Assert.assertFalse(beanUnderTest.isBeschikbaar("Karel"));
//	}
}
