package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;
import se.skillytaire.belastingdienst.ee.entity.VerhuurderBuilder;
import se.skillytaire.java.datatype.PositiveInteger;

public class BootJpaDaoTest {
	@Rule
	public EntityManagerTestRule jpa = EntityManagerTestRule.persistenceUnit("stuga");

	@This
	private Boot thisBoot;
	@This
	private Boot thisBoot2;
	@This
	private Adres adres;
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
		beanUnderTest = new BootJpaDAO();
		beanUnderTest.setEntityManager(jpa.em());
	}

	@Test
	public void testNewBoot() {
		this.beanUnderTest.add(this.thisBoot);
		Assert.assertTrue(this.thisBoot.isPersistant());
	}

	private void addWithTX(final Boot boot) {
		EntityTransaction unmanagedTx = jpa.getNewTransaction();
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
		Verhuurder verhuurder = generateRandomHuurder();
		Boot boot1 = new Boot(verhuurder, this.thisNummer);
		Boot boot2 = new Boot(verhuurder, this.thatNummer);
		Boot boot3 = new Boot(verhuurder, this.lessThenNummer);
		Boot boot4 = new Boot(verhuurder, this.greaterThenNummer);
		this.addWithTX(boot1);
		this.addWithTX(boot2);
		this.addWithTX(boot3);
		this.addWithTX(boot4);
		boot1.start(eenTocht1);
		boot2.start(eenTocht2);
		boot3.start(eenTocht3);
		boot4.start(eenTocht4);
		boot2.beeindigLaatsteTocht();
		Optional<Boot> result = this.beanUnderTest.findBeschikbareBoot(verhuurder);
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(boot2, result.get());
	}

	@Test
	public void testDeleteByOid() {
		this.addWithTX(this.thisBoot);
		EntityTransaction unmanagedTx = jpa.getNewTransaction();
		unmanagedTx.begin();
		boolean actual = this.beanUnderTest.deleteByOID(this.thisBoot.getOid());
		Assert.assertTrue(actual);
		actual = this.beanUnderTest.deleteByOID(this.thisBoot.getOid());
		Assert.assertFalse(actual);
		unmanagedTx.commit();
	}

	@Test
	public void testDeleteByNonExisitingOid() {
		EntityTransaction unmanagedTx = jpa.getNewTransaction();
		unmanagedTx.begin();
		boolean actual = this.beanUnderTest.deleteByOID(9999999);
		Assert.assertFalse(actual);
		unmanagedTx.commit();
	}

	@Test
	public void testDeleteExisting() {
		this.addWithTX(this.thisBoot);
		EntityTransaction unmanagedTx = jpa.getNewTransaction();
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
		EntityTransaction unmanagedTx = jpa.getNewTransaction();
		unmanagedTx.begin();
		this.beanUnderTest.update(this.thisBoot);
		Boot persistant = this.beanUnderTest.update(this.thisBoot);
		unmanagedTx.commit();
		Assert.assertFalse(this.thisBoot.isPersistant());
		Assert.assertTrue(persistant.isPersistant());
		Assert.assertEquals(this.thisBoot, persistant);
	}
	
	private Verhuurder generateRandomHuurder() {
		return VerhuurderBuilder.builder().withAdres(adres).withName(UUID.randomUUID().toString())
				.withUserName(UUID.randomUUID().toString()).build();
	}
}
