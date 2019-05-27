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
import se.skillytaire.belastingdienst.ee.service.ejb.PasswordCheckEJB;
import se.skillytaire.course.tools.jlc.JLCRunner;

public class PasswordCheckTest {
	@Rule
	public EntityManagerTestRule jpa = EntityManagerTestRule.persistenceUnit("stuga");
	private PasswordCheckEJB beanUnderTest;
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
		beanUnderTest = new PasswordCheckEJB(beanUnderTest2);
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
	public void isNietValideTest() {
		Klant klant = KlantBuilder.builder().withPassword("123").withUsername("Karel").withEmail("sexyboy@gmail.com")
				.build();
		beanUnderTest2.add(klant);
		this.addWithTX(klant);
		Assert.assertFalse(beanUnderTest.isValide("Karel", "SexyBoy"));
	}
	
	@Test
	public void isValideTest() {
		Klant klant = KlantBuilder.builder().withPassword("123").withUsername("Karel").withEmail("sexyboy@gmail.com")
				.build();
		beanUnderTest2.add(klant);
		this.addWithTX(klant);
		Assert.assertTrue(beanUnderTest.isValide("Karel", "123"));
	}
	
	@Test
	public void passwordCheck() {
		PasswordCheckEJB checker = new PasswordCheckEJB();
		checker.getClass();
	}
}
