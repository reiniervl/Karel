package se.skillytaire.belastingdienst.ee.persistance.jpa;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.DAO;
import se.skillytaire.belastingdienst.ee.persistance.VerhuurderDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class VerhuurderJpaDAOTest {
   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Verhuurder thisVerhuurder;
   @This
	 private Verhuurder thisVerhuurder2;

   private VerhuurderDAO beanUnderTest;

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   // every time thedb will be truncated
   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      VerhuurderJpaDAO.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = VerhuurderJpaDAO.getInstance();
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
   public void testDaoAdd() {
      assertNotNull("EntityManage cannot be null", this.entityManager);
      this.beanUnderTest.add(thisVerhuurder);
      Assert.assertTrue("Verhuurder is opgeslagen", thisVerhuurder.isPersistant());

   }

   @Test
   public void testNewVerhuurder() {
      this.beanUnderTest.add(this.thisVerhuurder);
      Assert.assertTrue(this.thisVerhuurder.isPersistant());
   }

   private void addWithTX(final Verhuurder Verhuurder) {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         this.beanUnderTest.add(Verhuurder);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

  //  @Test(expected = RollbackException.class)
  //  public void testAddedTwiceVerhuurder() {
  //     this.addWithTX(this.thisVerhuurder);
  //     Assert.assertTrue(this.thisVerhuurder.isPersistant());
  //     Assert.assertFalse(this.thisVerhuurder.isIdentical(this.thisVerhuurder2));
  //     this.addWithTX(this.thisVerhuurder2);
  //  }

   @Test
   public void testFindByNonExistingOid() {
      Optional<Verhuurder> result = this.beanUnderTest.findByOID(999999999);
      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testFindByExistingOid() {
      Assert.assertFalse(this.thisVerhuurder.isPersistant());
      this.addWithTX(this.thisVerhuurder);
      Optional<Verhuurder> result = this.beanUnderTest
            .findByOID(this.thisVerhuurder.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisVerhuurder, result.get());
	 }
	 
	 @Test
	 public void testFindByUsername() {
		Assert.assertFalse(this.thisVerhuurder.isPersistant());
		this.addWithTX(this.thisVerhuurder);
		Assert.assertTrue(this.thisVerhuurder.isPersistant());
		Optional<Verhuurder> result = this.beanUnderTest.find(this.thisVerhuurder.getUserName());
		Assert.assertTrue(result.isPresent());
	 }

   @Test
   public void testDeleteByOid() {
      this.addWithTX(this.thisVerhuurder);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = this.beanUnderTest.deleteByOID(this.thisVerhuurder.getOid());
      Assert.assertTrue(actual);
      actual = this.beanUnderTest.deleteByOID(this.thisVerhuurder.getOid());
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
      this.addWithTX(this.thisVerhuurder);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(this.thisVerhuurder);
      Assert.assertTrue(succes);
      // oid is still there check jpa
      Optional<Verhuurder> result = this.beanUnderTest
            .findByOID(this.thisVerhuurder.getOid());
      Assert.assertFalse(result.isPresent());
      unmanagedTx.commit();
   }

  //  @Test
  //  public void testUpdate() {
  //     this.addWithTX(this.thisVerhuurder);
  //     EntityTransaction unmanagedTx = this.entityManager.getTransaction();
  //     unmanagedTx.begin();
  //     Verhuurder clone = this.thisVerhuurder.clone();
  //     clone.setEmail("testing@test.com");
  //     this.beanUnderTest.update(clone);
  //     Optional<Verhuurder> result = this.beanUnderTest
  //           .findByOID(this.thisVerhuurder.getOid());
  //     Assert.assertTrue(result.isPresent());
  //     Assert.assertEquals("testing@test.com", result.get().getEmail());
  //     unmanagedTx.commit();
  //  }

   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Verhuurder persistant = this.beanUnderTest.update(this.thisVerhuurder);
      Assert.assertFalse(this.thisVerhuurder.isPersistant());
      Assert.assertTrue(persistant.isPersistant());
      Assert.assertEquals(this.thisVerhuurder, persistant);
      unmanagedTx.commit();

   }
}
