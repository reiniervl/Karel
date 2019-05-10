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

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class RivierTochtJpaDAOTest {

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@This
	private RivierTocht thisRivierTocht;
	@This
	private RivierTocht thisRivierTocht2;

	private RivierTochtJpaDAO beanUnderTest;

	private double testPrijs = 66D;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		this.factory = Persistence.createEntityManagerFactory("stuga");
		this.entityManager = this.factory.createEntityManager();
		RivierTochtJpaDAO.getInstance().setEntityManager(this.entityManager);
		this.beanUnderTest = RivierTochtJpaDAO.getInstance();
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
	public void testNewRivierTocht() {
		this.beanUnderTest.add(this.thisRivierTocht);
		Assert.assertTrue(this.thisRivierTocht.isPersistant());
	}

	private void addWithTX(final RivierTocht rivierTocht) {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		try {
			unmanagedTx.begin();
			this.beanUnderTest.add(rivierTocht);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}

	@Test(expected = RollbackException.class)
	public void testAddedTwiceRivierTocht() {
		this.thisRivierTocht.setPrijs(testPrijs);
		this.addWithTX(this.thisRivierTocht);
		Assert.assertTrue(this.thisRivierTocht.isPersistant());
		Assert.assertFalse(this.thisRivierTocht.isIdentical(this.thisRivierTocht2));
		this.addWithTX(this.thisRivierTocht2);
	}

	@Test
	public void testFindByNonExistingOid() {
		Optional<RivierTocht> result = this.beanUnderTest.findByOID(999999999);
		Assert.assertFalse(result.isPresent());
	}

	@Test
	public void testFindByExistingOid() {
		Assert.assertFalse(this.thisRivierTocht.isPersistant());
		this.addWithTX(this.thisRivierTocht);
		Optional<RivierTocht> result = this.beanUnderTest.findByOID(this.thisRivierTocht.getOid());
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(this.thisRivierTocht, result.get());
	}

	@Test
	public void testDeleteByOid() {
		this.addWithTX(this.thisRivierTocht);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean actual = this.beanUnderTest.deleteByOID(this.thisRivierTocht.getOid());
		Assert.assertTrue(actual);
		actual = this.beanUnderTest.deleteByOID(this.thisRivierTocht.getOid());
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
		this.addWithTX(this.thisRivierTocht);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean succes = this.beanUnderTest.delete(this.thisRivierTocht);
		Assert.assertTrue(succes);
		// oid is still there check jpa
		Optional<RivierTocht> result = this.beanUnderTest.findByOID(this.thisRivierTocht.getOid());
		Assert.assertFalse(result.isPresent());
		unmanagedTx.commit();
	}

	@Test
	public void testUpdate() {
		this.addWithTX(this.thisRivierTocht);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		RivierTocht clone = this.thisRivierTocht.clone();
		clone.setPrijs(testPrijs);
		this.beanUnderTest.update(clone);
		Optional<RivierTocht> result = this.beanUnderTest.findByOID(this.thisRivierTocht.getOid());
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(testPrijs, result.get().getPrijs(), 0);
		unmanagedTx.commit();
	}

	@Test
	public void testUpdateNonExsisting() {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		this.beanUnderTest.update(this.thisRivierTocht2);
		RivierTocht persistant = this.beanUnderTest.update(this.thisRivierTocht2);
		unmanagedTx.commit();
		Assert.assertFalse(this.thisRivierTocht2.isPersistant());
		Assert.assertTrue(persistant.isPersistant());
		Assert.assertEquals(this.thisRivierTocht2, persistant);
	}
}
