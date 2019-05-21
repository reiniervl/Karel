package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.time.LocalDateTime;
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

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.persistance.ReserveringDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

public class ReserveringJpaDAOTest {

   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Reservering thisReservering;
   @This
   private Reservering thisReservering2;

   private ReserveringDAO beanUnderTest;

   private LocalDateTime testTijd = LocalDateTime.now();

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      ReserveringJpaDAO.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = ReserveringJpaDAO.getInstance();
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
   public void testNewReservering() {
      this.beanUnderTest.add(this.thisReservering);
      Assert.assertTrue(this.thisReservering.isPersistant());
   }

   private void addWithTX(final Reservering reservering) {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         this.beanUnderTest.add(reservering);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

   @Test(expected = RollbackException.class)
   public void testAddedTwiceReservering() {
      this.thisReservering.setReserveringsDatum(testTijd);
      this.addWithTX(this.thisReservering);
      Assert.assertTrue(this.thisReservering.isPersistant());
      Assert.assertFalse(this.thisReservering.isIdentical(this.thisReservering2));
      this.addWithTX(this.thisReservering2);
   }

   @Test
   public void testFindByNonExistingOid() {
      Optional<Reservering> result = this.beanUnderTest.findByOID(999999999);
      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testFindByExistingOid() {
      Assert.assertFalse(this.thisReservering.isPersistant());
      this.addWithTX(this.thisReservering);
      Optional<Reservering> result =
            this.beanUnderTest.findByOID(this.thisReservering.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisReservering, result.get());
   }

   @Test
   public void testDeleteByOid() {
      this.addWithTX(this.thisReservering);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = this.beanUnderTest.deleteByOID(this.thisReservering.getOid());
      Assert.assertTrue(actual);
      actual = this.beanUnderTest.deleteByOID(this.thisReservering.getOid());
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
      this.addWithTX(this.thisReservering);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(this.thisReservering);
      Assert.assertTrue(succes);
      // oid is still there check jpa
      Optional<Reservering> result =
            this.beanUnderTest.findByOID(this.thisReservering.getOid());
      Assert.assertFalse(result.isPresent());
      unmanagedTx.commit();
   }



   @Test
   public void testUpdate() {
      this.addWithTX(this.thisReservering);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Reservering clone = this.thisReservering.clone();
      clone.setReserveringsDatum(testTijd);
      this.beanUnderTest.update(clone);
      Optional<Reservering> result =
            this.beanUnderTest.findByOID(this.thisReservering.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(testTijd, result.get().getReserveringsDatum());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Reservering persistant = this.beanUnderTest.update(this.thisReservering);
      Assert.assertFalse(this.thisReservering.isPersistant());
      Assert.assertTrue(persistant.isPersistant());
      Assert.assertEquals(this.thisReservering, persistant);
      unmanagedTx.commit();
   }
   @This
   private Account thisAccount;

   @This
   private MeerTocht thisMeerTocht;
   @Test
   public void testGetReserveringsNummer() {
      Reservering reservering1 = new Reservering(thisAccount, thisMeerTocht);
      this.beanUnderTest.add(reservering1);
      Optional<Integer> result = reservering1.getReserveringsNummer();
      Assert.assertFalse(result.equals(null));
   }
}
