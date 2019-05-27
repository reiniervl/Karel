package se.skillytaire.belastingdienst.ee.service;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.KlantBuilder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.EntityManagerTestRule;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.ejb.UsernameCheckEJB;
import se.skillytaire.course.tools.jlc.JLCRunner;

public class UserNameCheckTest {
	@Rule
	public EntityManagerTestRule jpa = EntityManagerTestRule.persistenceUnit("stuga");
	private UsernameCheckEJB beanUnderTest;
	private KlantJpaDAO beanUnderTest2;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		JLCRunner.init(this);
		beanUnderTest2 = new KlantJpaDAO();
	    beanUnderTest2.setEntityManager(jpa.em());
		beanUnderTest = new UsernameCheckEJB(beanUnderTest2);
	}

	private void addWithTX(final Klant klant) {
		EntityTransaction unmanagedTx = jpa.getNewTransaction();
		try {
			unmanagedTx.begin();
			beanUnderTest2.add(klant);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}

	@Test
	public void isBeschikbaarTest() {
		Assert.assertTrue(beanUnderTest.isBeschikbaar("Karel"));
	}

	@Test
	public void isNietBeschikbaarTest() {
		Klant klant = KlantBuilder.builder().withPassword("123").withUsername("Karel").withEmail("sexyboy@gmail.com")
				.build();
		beanUnderTest2.add(klant);
		this.addWithTX(klant);
		Assert.assertFalse(beanUnderTest.isBeschikbaar("Karel"));
	}
	
	@Test
	public void usernameCheck() {
		UsernameCheckEJB checker = new UsernameCheckEJB();
		checker.getClass();
	}
}
