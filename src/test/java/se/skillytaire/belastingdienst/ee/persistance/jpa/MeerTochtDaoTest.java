package se.skillytaire.belastingdienst.ee.persistance.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class MeerTochtDaoTest {

   private EntityManagerFactory factory;
   private EntityManager entityManager;

   @This
   private Periode thisPeriode;
   @This
   private MeerTocht thisMeerTocht;
   private MeerTochtDAO beanUnderTest;

   @Before
   public void before() {
      JLCRunner.init(this);
   }

   // every time thedb will be truncated
   @Before
   public void initJPA() {
      this.factory = Persistence.createEntityManagerFactory("stuga");
      this.entityManager = this.factory.createEntityManager();
      MeerTochtDAO.getInstance().setEntityManager(this.entityManager);
      this.beanUnderTest = MeerTochtDAO.getInstance();
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
   public void testDAOadd2() {
      this.beanUnderTest.add(this.thisMeerTocht);
      Assert.assertTrue(this.thisMeerTocht.isPersistant());

   }

   @Test
   public void testDaoAdd() {
      MeerTocht nieuweMeerTocht = new MeerTocht(10D, thisPeriode);
      MeerTochtDAO dao = MeerTochtDAO.getDAO();
      Assert.assertFalse(dao == null);
      Assert.assertFalse(nieuweMeerTocht == null);
      dao.add(nieuweMeerTocht);
      Assert.assertTrue("MeerTocht is opgeslagen",
            nieuweMeerTocht.isPersistant());
   }

   @Test
   public void testMeerTochtUpdate() {
      MeerTocht updateMeerTocht = new MeerTocht(12D, thisPeriode);
      MeerTochtDAO dao = MeerTochtDAO.getDAO();
      dao.add(updateMeerTocht);
      Assert.assertTrue("MeerTocht is opgeslagen",
            updateMeerTocht.isPersistant());
      MeerTocht clone = this.thisMeerTocht.clone();
      clone.setPrijs(16D);
      // nieuw meertocht maken,
      // meertocht opslaan in database
      // meertocht ophalen uit database
      // meertocht aanpassen
      // meertocht opslaan in database
      // meertocht ophalen uit database
      // update toegepast op meertocht

   }
}
//
// public void testUpdate() {
// this.addWithTX(this.);
// EntityTransaction unmanagedTx = this.entityManager.getTransaction();
// unmanagedTx.begin();
// Adres clone = this.thisAdres.clone();
// clone.setCity("Zwolle");
// this.beanUnderTest.update(clone);
// Optional<Adres> result = this.beanUnderTest
// .findByOID(this.thisAdres.getOid());
// Assert.assertTrue(result.isPresent());
// Assert.assertEquals("Zwolle", result.get().getCity());
// unmanagedTx.commit();
//
//
// }
