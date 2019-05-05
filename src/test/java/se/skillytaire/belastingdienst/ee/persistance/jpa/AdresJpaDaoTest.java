package se.skillytaire.belastingdienst.ee.persistance.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.persistance.AdresDao;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.Property;
import se.skillytaire.course.tools.jlc.TestPropertyFile;
import se.skillytaire.course.tools.jlc.That;
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

   //every time thedb will be truncated
   @Before
   public void initJPA() {
      factory = Persistence
            .createEntityManagerFactory("stuga");
      entityManager = factory.createEntityManager();
      AdresJpaDao.getInstance().setEntityManager(entityManager);
      beanUnderTest = AdresJpaDao.getInstance();
   }

   @After
   public  void destroyJPA() {
      entityManager.close();
      factory.close();
      while (factory.isOpen() && !Thread.interrupted()) {
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         }
      }
   }

   @Test
   public void testNewAdres() {
      beanUnderTest.add(thisAdres);
      assertTrue(this.thisAdres.isPersistant());
   }

   private void addWithTX(Adres adres) {
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         beanUnderTest.add(adres);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

   @Test(expected=RollbackException.class)
   public void testAddedTwiceAdres() {
      thisAdres.setCity("Zwolle");
      addWithTX(thisAdres);
      assertTrue(this.thisAdres.isPersistant());
      assertFalse(this.thisAdres.isIdentical(thisAdres2));
      addWithTX(thisAdres2);
   }
   @Test
   public void testFindByNonExistingOid() {
      Optional<Adres> result = beanUnderTest.findByOID(999999999);
      assertFalse(result.isPresent());
   }
   
   @Test
   public void testFindByExistingOid() {
      assertFalse(thisAdres.isPersistant());
      this.addWithTX(thisAdres);
      Optional<Adres> result = beanUnderTest.findByOID(thisAdres.getOid());
      assertTrue(result.isPresent());
      assertEquals(thisAdres, result.get());
   }
   @Test
   public void testFindByGPS() {
      assertFalse(thisAdres.isPersistant());
      this.addWithTX(thisAdres);
      EmbeddableGPSCoordinaat aGPS = thisAdres.getGpsCoordinaat();
      GPSCoordinaat coordinate = new GPSCoordinaat(aGPS.getLongtitude(), aGPS.getLatitude());
      
      Optional<Adres> result = beanUnderTest.find(coordinate);
      assertTrue(result.isPresent());
      assertEquals(thisAdres, result.get());
   }
   @Test
   public void testDeleteByOid() {
      this.addWithTX(thisAdres);
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = beanUnderTest.deleteByOID(thisAdres.getOid());
      assertTrue(actual); 
      actual = beanUnderTest.deleteByOID(thisAdres.getOid());
      assertFalse(actual);
      unmanagedTx.commit();
   } 
   @Test
   public void testDeleteByNonExisitingOid() {
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = beanUnderTest.deleteByOID(9999999);
      assertFalse(actual);  
      unmanagedTx.commit();
   }
   @Test
   public void testDeleteExisting() {
      this.addWithTX(thisAdres);
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(thisAdres);
      assertTrue(succes);
      //oid is still there check jpa
      Optional<Adres> result = beanUnderTest.findByOID(thisAdres.getOid());
      assertFalse(result.isPresent());
      unmanagedTx.commit();
   }
  
   @Test
   public void testUpdate() {
      this.addWithTX(thisAdres);
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      unmanagedTx.begin();
      Adres clone = this.thisAdres.clone();
      clone.setCity("Zwolle");
      beanUnderTest.update(clone);
      Optional<Adres> result = beanUnderTest.findByOID(thisAdres.getOid());
      assertTrue(result.isPresent());
      assertEquals("Zwolle", result.get().getCity());
      unmanagedTx.commit();
   }
   
   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      unmanagedTx.begin();
      Adres persistant = beanUnderTest.update(this.thisAdres);
      assertFalse(thisAdres.isPersistant());
      assertTrue(persistant.isPersistant());
      assertEquals(thisAdres, persistant);
      unmanagedTx.commit();
      
   }
}
