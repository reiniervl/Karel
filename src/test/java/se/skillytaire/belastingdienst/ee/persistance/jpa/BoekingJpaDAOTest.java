package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Boeking;
import se.skillytaire.belastingdienst.ee.persistance.BoekingDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class BoekingJpaDAOTest {

   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Boeking thisBoeking1;
   @This
   private Boeking thisBoeking2;

   private BoekingDAO beanUnderTest;

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      BoekingJpaDAO.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = BoekingJpaDAO.getInstance();
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
   public void testNewBoeking() {
      this.beanUnderTest.add(this.thisBoeking1);
      Assert.assertTrue(this.thisBoeking1.isPersistant());
   }

   private void addWithTX(final Boeking boeking) {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         this.beanUnderTest.add(boeking);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

//   @Test(expected = RollbackException.class)
//   public void testAddedTwiceBoeking() {
//      this.addWithTX(this.thisBoeking1);
//      Assert.assertTrue(this.thisBoeking1.isPersistant());
//      this.addWithTX(this.thisBoeking1);
//      Assert.assertFalse(this.thisBoeking1.isIdentical(this.thisBoeking2));
//      this.addWithTX(this.thisBoeking1);
//   }

   @Test
   public void testFindByNonExistingOid() {
      Optional<Boeking> result = this.beanUnderTest.findByOID(999999999);
      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testFindByExistingOid() {
      Assert.assertFalse(this.thisBoeking1.isPersistant());
      this.addWithTX(this.thisBoeking1);
      Optional<Boeking> result =
            this.beanUnderTest.findByOID(this.thisBoeking1.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisBoeking1, result.get());
   }

   @Test
   public void testDeleteByOid() {
      this.addWithTX(this.thisBoeking1);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = this.beanUnderTest.deleteByOID(this.thisBoeking1.getOid());
      Assert.assertTrue(actual);
      actual = this.beanUnderTest.deleteByOID(this.thisBoeking1.getOid());
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
      this.addWithTX(this.thisBoeking1);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(this.thisBoeking1);
      Assert.assertTrue(succes);
      // oid is still there check jpa
      Optional<Boeking> result =
            this.beanUnderTest.findByOID(this.thisBoeking1.getOid());
      Assert.assertFalse(result.isPresent());
      unmanagedTx.commit();
   }



   @Test
   public void testUpdate() {
      this.addWithTX(this.thisBoeking1);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Boeking clone = this.thisBoeking1.clone();
      this.beanUnderTest.update(clone);
      Optional<Boeking> result =
            this.beanUnderTest.findByOID(this.thisBoeking1.getOid());
      Assert.assertTrue(result.isPresent());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Boeking persistant = this.beanUnderTest.update(this.thisBoeking1);
      Assert.assertFalse(this.thisBoeking1.isPersistant());
      Assert.assertTrue(persistant.isPersistant());
      Assert.assertEquals(this.thisBoeking1, persistant);
      unmanagedTx.commit();
   }
}
