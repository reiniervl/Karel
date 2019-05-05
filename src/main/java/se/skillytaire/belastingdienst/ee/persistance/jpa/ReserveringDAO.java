package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class ReserveringDAO implements DAO<Reservering> {

   @Override
   public void add(final Reservering that) {
      EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      try {
         unmanagedTx.begin();
         entityManager.persist(that);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx != null) {
            unmanagedTx.rollback();
         }
         throw e;
      }
      entityManager.close();
      entityManagerFactory.close();
   }

   @Override
   public Optional<Reservering> findByOID(final Integer OID) {
      EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      Reservering gevondenReservering = entityManager.find(Reservering.class,
            OID);
      Optional<Reservering> optioneleReservering = Optional
            .ofNullable(gevondenReservering);
      entityManager.close();
      entityManagerFactory.close();
      return optioneleReservering;
   }

   @Override
   public Reservering update(final Reservering that) {
      Reservering result;
      EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      Reservering updateReservering = that;
      try {
         unmanagedTx.begin();
         result = entityManager.merge(updateReservering);
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx != null) {
            unmanagedTx.rollback();
         }
         throw e;
      }
      return result;
   }

   @Override
   public boolean delete(final Reservering that) {
      EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      Reservering deleteReservering = that;
      try {
         unmanagedTx.begin();
         entityManager.remove(deleteReservering);
         if (deleteReservering == null) {
            throw new IllegalArgumentException("Reservering bestaat niet!");
         }
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx != null) {
            unmanagedTx.rollback();
         }
         throw e;
      }
      return true;
   }

   @Override
   public boolean deleteByOID(final Integer OID) {
      EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction unmanagedTx = entityManager.getTransaction();
      Reservering deleteReserveringByOID;
      try {
         unmanagedTx.begin();
         deleteReserveringByOID = entityManager.find(Reservering.class, OID);
         entityManager.remove(deleteReserveringByOID);
         if (deleteReserveringByOID == null) {
            throw new IllegalArgumentException(
                  "Reservering met opgegeven OID bestaat niet!");
         }
         unmanagedTx.commit();
      } catch (RuntimeException e) {
         if (unmanagedTx != null) {
            unmanagedTx.rollback();
         }
         throw e;
      }
      return true;
   }

}
