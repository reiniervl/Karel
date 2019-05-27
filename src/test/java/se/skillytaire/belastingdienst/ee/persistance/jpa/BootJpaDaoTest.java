package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;
import se.skillytaire.java.datatype.PositiveInteger;

public class BootJpaDaoTest {

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@This
	private Boot thisBoot;
	@This
	private Boot thisBoot2;
	@That
	private Verhuurder verhuurder;
	@This
	private PositiveInteger thisNummer;
	@That
	private PositiveInteger thatNummer;
	@LessThen
	private PositiveInteger lessThenNummer;
	@GreaterThen
	private PositiveInteger greaterThenNummer;
	@This
	private RivierTocht eenTocht1;
	@That
	private RivierTocht eenTocht2;
	@LessThen
	private RivierTocht eenTocht3;
	@GreaterThen
	private RivierTocht eenTocht4;

	private BootJpaDAO beanUnderTest;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		this.factory = Persistence.createEntityManagerFactory("stuga");
		this.entityManager = this.factory.createEntityManager();
		BootJpaDAO.getInstance().setEntityManager(this.entityManager);
		this.beanUnderTest = BootJpaDAO.getInstance();
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

	@Test
	public void testNewBoot() {
		this.beanUnderTest.add(this.thisBoot);
		Assert.assertTrue(this.thisBoot.isPersistant());
	}

	private void addWithTX(final Boot boot) {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		try {
			unmanagedTx.begin();
			this.beanUnderTest.add(boot);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}
//
//	@Test(expected = RollbackException.class)
//	public void testAddedTwiceBootTocht() {
//		this.addWithTX(this.thisBoot);
//		Assert.assertTrue(this.thisBoot.isPersistant());
//		Assert.assertFalse(this.thisBoot.isIdentical(this.thisBoot2));
//		this.addWithTX(this.thisBoot2);
//	}

	@Test
	public void testFindByNonExistingOid() {
		Optional<Boot> result = this.beanUnderTest.findByOID(999999999);
		Assert.assertFalse(result.isPresent());
	}

	@Test
	public void testFindByExistingOid() {
		Assert.assertFalse(this.thisBoot.isPersistant());
		this.addWithTX(this.thisBoot);
		Optional<Boot> result = this.beanUnderTest.findByOID(this.thisBoot.getOid());
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(this.thisBoot, result.get());
	}

	@Test
	public void testFindBeschikbareBoot() {
		Boot boot1 = new Boot(this.verhuurder, this.thisNummer);
		Boot boot2 = new Boot(this.verhuurder, this.thatNummer);
		Boot boot3 = new Boot(this.verhuurder, this.lessThenNummer);
		Boot boot4 = new Boot(this.verhuurder, this.greaterThenNummer);
		this.addWithTX(boot1);
		this.addWithTX(boot2);
		this.addWithTX(boot3);
		this.addWithTX(boot4);
		boot1.start(eenTocht1);
		boot2.start(eenTocht2);
		boot3.start(eenTocht3);
		boot4.start(eenTocht4);
		boot2.beeindigLaatsteTocht();
		Optional<Boot> result = this.beanUnderTest.findBeschikbareBoot(this.verhuurder);
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(boot2, result.get());
	}

	@Test
	public void testDeleteByOid() {
		this.addWithTX(this.thisBoot);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean actual = this.beanUnderTest.deleteByOID(this.thisBoot.getOid());
		Assert.assertTrue(actual);
		actual = this.beanUnderTest.deleteByOID(this.thisBoot.getOid());
		Assert.assertFalse(actual);
		unmanagedTx.commit();
	}

	@Test
	public void testDeleteByNonExisitingOid() {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean actual = this.beanUnderTest.deleteByOID(9999999);
		Assert.assertFalse(actual);
		unmanagedTx.commit();
	}

	@Test
	public void testDeleteExisting() {
		this.addWithTX(this.thisBoot);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean succes = this.beanUnderTest.delete(this.thisBoot);
		Assert.assertTrue(succes);
		// oid is still there check jpa
		Optional<Boot> result = this.beanUnderTest.findByOID(this.thisBoot.getOid());
		Assert.assertFalse(result.isPresent());
		unmanagedTx.commit();
	}
//
//	@Test
//	public void testUpdate() {
//		this.addWithTX(this.thisBoot);
//		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
//		unmanagedTx.begin();
//		Boot clone = this.thisBoot.clone();
//		this.beanUnderTest.update(clone);
//		Optional<Boot> result = this.beanUnderTest.findByOID(this.thisBoot.getOid());
//		Assert.assertTrue(result.isPresent());
//		unmanagedTx.commit();
//	}

	@Test
	public void testUpdateNonExsisting() {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		this.beanUnderTest.update(this.thisBoot);
		Boot persistant = this.beanUnderTest.update(this.thisBoot);
		unmanagedTx.commit();
		Assert.assertFalse(this.thisBoot.isPersistant());
		Assert.assertTrue(persistant.isPersistant());
		Assert.assertEquals(this.thisBoot, persistant);
	}
}
