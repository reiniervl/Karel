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

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class AccountJpaDAOTest {
   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Account thisAccount;
   @This
   private Account thisAccount2;

   private AccountDAO beanUnderTest;

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   // every time thedb will be truncated
   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      AccountJpaDAO.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = AccountJpaDAO.getInstance();
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
      this.beanUnderTest.add(thisAccount);
      Assert.assertTrue("Account is opgeslagen", thisAccount.isPersistant());

   }

   @Test
   public void testNewAccount() {
      this.beanUnderTest.add(this.thisAccount);
      Assert.assertTrue(this.thisAccount.isPersistant());
   }

   private void addWithTX(final Account Account) {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         this.beanUnderTest.add(Account);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx.isActive()) {
            unmanagedTx.rollback();
         }
         throw e;
      }
   }

   @Test(expected = RollbackException.class)
   public void testAddedTwiceAccount() {
      this.thisAccount.getKlant().setEmail("testing@test.com");
      this.addWithTX(this.thisAccount);
      Assert.assertTrue(this.thisAccount.isPersistant());
      Assert.assertFalse(this.thisAccount.isIdentical(this.thisAccount2));
      this.addWithTX(this.thisAccount2);
   }

   @Test
   public void testFindByNonExistingOid() {
      Optional<Account> result = this.beanUnderTest.findByOID(999999999);
      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testFindByExistingOid() {
      Assert.assertFalse(this.thisAccount.isPersistant());
      this.addWithTX(this.thisAccount);
      Optional<Account> result = this.beanUnderTest
            .findByOID(this.thisAccount.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisAccount, result.get());
   }

   @Test
   public void testFindByUsername() {
      Assert.assertFalse(this.thisAccount.isPersistant());
      this.addWithTX(this.thisAccount);
      Optional<Account> result = this.beanUnderTest
            .find(this.thisAccount.getKlant().getUsername());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals(this.thisAccount, result.get());
   }

   @Test
   public void testDeleteByOid() {
      this.addWithTX(this.thisAccount);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean actual = this.beanUnderTest.deleteByOID(this.thisAccount.getOid());
      Assert.assertTrue(actual);
      actual = this.beanUnderTest.deleteByOID(this.thisAccount.getOid());
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
      this.addWithTX(this.thisAccount);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      boolean succes = this.beanUnderTest.delete(this.thisAccount);
      Assert.assertTrue(succes);
      // oid is still there check jpa
      Optional<Account> result = this.beanUnderTest
            .findByOID(this.thisAccount.getOid());
      Assert.assertFalse(result.isPresent());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdate() {
      this.addWithTX(this.thisAccount);
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Account clone = this.thisAccount.clone();
      clone.getKlant().setEmail("testing@test.com");
      this.beanUnderTest.update(clone);
      Optional<Account> result = this.beanUnderTest
            .findByOID(this.thisAccount.getOid());
      Assert.assertTrue(result.isPresent());
      Assert.assertEquals("testing@test.com", result.get().getKlant().getEmail());
      unmanagedTx.commit();
   }

   @Test
   public void testUpdateNonExsisting() {
      EntityTransaction unmanagedTx = this.entityManager.getTransaction();
      unmanagedTx.begin();
      Account persistant = this.beanUnderTest.update(this.thisAccount);
      Assert.assertFalse(this.thisAccount.isPersistant());
      Assert.assertTrue(persistant.isPersistant());
      Assert.assertEquals(this.thisAccount, persistant);
      unmanagedTx.commit();

   }
}
