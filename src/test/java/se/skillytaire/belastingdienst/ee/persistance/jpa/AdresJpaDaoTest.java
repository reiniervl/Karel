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

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.persistance.AdresDao;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class AdresJpaDaoTest {

   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Adres thisAdres;
   @This
   private Adres thisAdres2;

   private AdresDao beanUnderTest;

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   // every time thedb will be truncated
   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      AdresJpaDao.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = AdresJpaDao.getInstance();
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
   public void testNewAdres() {
      this.beanUnderTest.add(this.thisAdres);
      Assert.assertTrue(this.thisAdres.isPersistant());
   }

   private void addWithTX(final Adres adres) {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         this.beanUnderTest.add(adres);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

   @Test(expected = RollbackException.class)
   public void testAddedTwiceAdres() {
      this.thisAdres.setCity("Zwolle");
      this.addWithTX(this.thisAdres);
      Assert.assertTrue(this.thisAdres.isPersistant());
      Assert.assertFalse(this.thisAdres.isIdentical(this.thisAdres2));
      this.addWithTX(this.thisAdres2);
   }

   @Test
   public void testFindByNonExistingOid() {
      Optional<Adres> result = this.beanUnderTest.findByOID(999999999);
      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testFindByExistingOid() {
      Assert.assertFalse(this.thisAdres.isPersistant());
      this.addWithTX(this.thisAdres);
      Optional<Adres> result = this.beanUnderTest
            .findByOID(this.thisAdres.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisAdres, result.get());
   }

   @Test
   public void testFindByGPS() {
      Assert.assertFalse(this.thisAdres.isPersistant());
      this.addWithTX(this.thisAdres);
      EmbeddableGPSCoordinaat aGPS = this.thisAdres.getGpsCoordinaat();
      GPSCoordinaat coordinate = new GPSCoordinaat(aGPS.getLongtitude(),
            aGPS.getLatitude());

      Optional<Adres> result = this.beanUnderTest.find(coordinate);
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisAdres, result.get());
   }

   @Test
   public void testDeleteByOid() {
      this.addWithTX(this.thisAdres);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = this.beanUnderTest.deleteByOID(this.thisAdres.getOid());
      Assert.assertTrue(actual);
      actual = this.beanUnderTest.deleteByOID(this.thisAdres.getOid());
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
      this.addWithTX(this.thisAdres);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(this.thisAdres);
      Assert.assertTrue(succes);
      // oid is still there check jpa
      Optional<Adres> result = this.beanUnderTest
            .findByOID(this.thisAdres.getOid());
      Assert.assertFalse(result.isPresent());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdate() {
      this.addWithTX(this.thisAdres);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Adres clone = this.thisAdres.clone();
      clone.setCity("Zwolle");
      this.beanUnderTest.update(clone);
      Optional<Adres> result = this.beanUnderTest
            .findByOID(this.thisAdres.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals("Zwolle", result.get().getCity());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Adres persistant = this.beanUnderTest.update(this.thisAdres);
      Assert.assertFalse(this.thisAdres.isPersistant());
      Assert.assertTrue(persistant.isPersistant());
      Assert.assertEquals(this.thisAdres, persistant);
      unmanagedTx.commit();

   }
}
