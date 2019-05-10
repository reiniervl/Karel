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
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class BootJpaDaoTest {

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@This
	private Boot thisBoot;
	@This
	private Boot thisBoot2;

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

	@Test(expected = RollbackException.class)
	public void testAddedTwiceBootTocht() {
		this.addWithTX(this.thisBoot);
		Assert.assertTrue(this.thisBoot.isPersistant());
		Assert.assertFalse(this.thisBoot.isIdentical(this.thisBoot2));
		this.addWithTX(this.thisBoot2);
	}

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

	@Test
	public void testUpdate() {
		this.addWithTX(this.thisBoot); 
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		Boot clone = this.thisBoot.clone();
		this.beanUnderTest.update(clone);
		Optional<Boot> result = this.beanUnderTest.findByOID(this.thisBoot.getOid());
		Assert.assertTrue(result.isPresent());
		unmanagedTx.commit();
	}

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
