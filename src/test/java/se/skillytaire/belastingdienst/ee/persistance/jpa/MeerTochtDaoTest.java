package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtDAO;

public class MeerTochtDaoTest {

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@This
	private Periode thisPeriode;
	@This
	private MeerTocht thisMeerTocht;
	@This
	private MeerTocht thisMeerTocht2;
	private MeerTochtDAO beanUnderTest;

	@This
	Boot dezeBoot;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		this.factory = Persistence.createEntityManagerFactory("stuga");
		this.entityManager = this.factory.createEntityManager();
		MeerTochtDAO.getInstance().setEntityManager(this.entityManager);
		this.beanUnderTest = MeerTochtDAO.getInstance();
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

	private void addWithTX(final MeerTocht meerTocht) {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		try {
			unmanagedTx.begin();
			this.beanUnderTest.add(meerTocht);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}

	@Test
	public void testDAOadd2() {
		this.beanUnderTest.add(this.thisMeerTocht);
		Assert.assertTrue(this.thisMeerTocht.isPersistant());

	}

	@Test
	public void testDaoAdd() {
		MeerTocht nieuweMeerTocht = new MeerTocht(dezeBoot, 10D, thisPeriode);
		MeerTochtDAO dao = MeerTochtDAO.getDAO();
		Assert.assertFalse(dao == null);
		Assert.assertFalse(nieuweMeerTocht == null);
		dao.add(nieuweMeerTocht);
		Assert.assertTrue("MeerTocht is opgeslagen", nieuweMeerTocht.isPersistant());
	}

	@Test(expected = RollbackException.class)
	public void testAddedTwiceMeerTocht() {
		this.thisMeerTocht.setPrijs(12);
		this.addWithTX(this.thisMeerTocht);
		Assert.assertTrue(this.thisMeerTocht.isPersistant());
		Assert.assertFalse(this.thisMeerTocht.isIdentical(this.thisMeerTocht2));
		this.addWithTX(this.thisMeerTocht2);
	}

	@Test
	public void testFindByNonExistingOid() {
		Optional<MeerTocht> result = this.beanUnderTest.findByOID(999999999);
		Assert.assertFalse(result.isPresent());
	}

	@Test
	public void testFindByExistingOid() {
		Assert.assertFalse(this.thisMeerTocht.isPersistant());
		this.addWithTX(this.thisMeerTocht);
		Optional<MeerTocht> result = this.beanUnderTest.findByOID(this.thisMeerTocht.getOid());
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(this.thisMeerTocht, result.get());
	}

	@Test
	public void testDeleteByOid() {
		this.addWithTX(this.thisMeerTocht);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean actual = this.beanUnderTest.deleteByOID(this.thisMeerTocht.getOid());
		Assert.assertTrue(actual);
		actual = this.beanUnderTest.deleteByOID(this.thisMeerTocht.getOid());
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
		this.addWithTX(this.thisMeerTocht);
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		boolean succes = this.beanUnderTest.delete(this.thisMeerTocht);
		Assert.assertTrue(succes);
		unmanagedTx.commit();
		Optional<MeerTocht> result = this.beanUnderTest.findByOID(this.thisMeerTocht.getOid());
		Assert.assertFalse(result.isPresent());
	}

	@Test
	public void testMeerTochtUpdate() {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		MeerTocht updateMeerTocht = new MeerTocht(dezeBoot, 10D, thisPeriode);
		MeerTochtDAO dao = MeerTochtDAO.getDAO();
		dao.add(updateMeerTocht);
		Assert.assertTrue("MeerTocht is opgeslagen", updateMeerTocht.isPersistant());
		updateMeerTocht.setPrijs(16D);
		this.beanUnderTest.update(updateMeerTocht);
		Optional<MeerTocht> result = this.beanUnderTest.findByOID(updateMeerTocht.getOid());
		Assert.assertTrue(result.isPresent());
		Assert.assertTrue(16D == result.get().getPrijs());
		unmanagedTx.commit();
	}

	@Test
	public void testUpdateNietBestaandeMeerTocht() {
		EntityTransaction unmanagedTx = this.entityManager.getTransaction();
		unmanagedTx.begin();
		MeerTocht persistant = this.beanUnderTest.update(this.thisMeerTocht);
		Assert.assertFalse(this.thisMeerTocht.isPersistant());
		Assert.assertTrue(persistant.isPersistant());
		Assert.assertEquals(this.thisMeerTocht, persistant);
		unmanagedTx.commit();
	}

}
