package se.skillytaire.belastingdienst.ee.persistance.jpa;

import static org.junit.Assert.assertNotNull;

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

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class KlantJpaDAOTest {
   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Klant thisKlant;
   @This
   private Klant thisKlant2;

   private KlantDAO beanUnderTest;

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   // every time thedb will be truncated
   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      KlantJpaDAO.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = KlantJpaDAO.getInstance();
   }

   @After
   public void destroyJPA() {
      this.entityManager.close();
      this.factory.close();
      while (this.factory.isOpen() && !Thread.interrupted()) {
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         }
      }
   }

   @Test
   public void testDaoAdd() {
      assertNotNull("EntityManage cannot be null", this.entityManager);
      this.beanUnderTest.add(thisKlant);
      Assert.assertTrue("Klant is opgeslagen", thisKlant.isPersistant());

   }

   @Test
   public void testNewKlant() {
      this.beanUnderTest.add(this.thisKlant);
      Assert.assertTrue(this.thisKlant.isPersistant());
   }

   private void addWithTX(final Klant klant) {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         this.beanUnderTest.add(klant);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

   @Test(expected = RollbackException.class)
   public void testAddedTwiceKlant() {
      this.thisKlant.setEmail("testing@test.com");
      this.addWithTX(this.thisKlant);
      Assert.assertTrue(this.thisKlant.isPersistant());
      Assert.assertFalse(this.thisKlant.isIdentical(this.thisKlant2));
      this.addWithTX(this.thisKlant2);
   }

   @Test
   public void testFindByNonExistingOid() {
      Optional<Klant> result = this.beanUnderTest.findByOID(999999999);
      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testFindByExistingOid() {
      Assert.assertFalse(this.thisKlant.isPersistant());
      this.addWithTX(this.thisKlant);
      Optional<Klant> result = this.beanUnderTest
            .findByOID(this.thisKlant.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisKlant, result.get());
   }

   @Test
   public void testDeleteByOid() {
      this.addWithTX(this.thisKlant);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = this.beanUnderTest.deleteByOID(this.thisKlant.getOid());
      Assert.assertTrue(actual);
      actual = this.beanUnderTest.deleteByOID(this.thisKlant.getOid());
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
      this.addWithTX(this.thisKlant);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(this.thisKlant);
      Assert.assertTrue(succes);
      // oid is still there check jpa
      Optional<Klant> result = this.beanUnderTest
            .findByOID(this.thisKlant.getOid());
      Assert.assertFalse(result.isPresent());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdate() {
      this.addWithTX(this.thisKlant);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Klant clone = this.thisKlant.clone();
      clone.setEmail("testing@test.com");
      this.beanUnderTest.update(clone);
      Optional<Klant> result = this.beanUnderTest
            .findByOID(this.thisKlant.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals("testing@test.com", result.get().getEmail());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Klant persistant = this.beanUnderTest.update(this.thisKlant);
      Assert.assertFalse(this.thisKlant.isPersistant());
      Assert.assertTrue(persistant.isPersistant());
      Assert.assertEquals(this.thisKlant, persistant);
      unmanagedTx.commit();

   }
}
