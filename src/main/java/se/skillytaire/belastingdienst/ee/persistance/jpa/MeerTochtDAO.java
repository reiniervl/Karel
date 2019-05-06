package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class MeerTochtDAO implements DAO<MeerTocht> {
   private final static MeerTochtDAO dao = new MeerTochtDAO();

   private MeerTochtDAO() {
   }

   public static MeerTochtDAO getDAO() {

      return MeerTochtDAO.dao;
   }

   @Override
   public void add(MeerTocht t) {
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      em.persist(t);
      em.close();
      factory.close();
   }

   @Override
   public Optional<MeerTocht> findByOID(Integer OID) {
      // TODO Auto-generated method stub
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      MeerTocht tochtinsysteem = em.find(MeerTocht.class, OID);
      Optional<MeerTocht> optioneleTocht = Optional.ofNullable(tochtinsysteem);

      em.close();
      factory.close();
      return optioneleTocht;
   }

   @Override
   public MeerTocht update(MeerTocht t) {
      // TODO Auto-generated method stub
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      MeerTocht result = em.merge(t);
      em.close();
      factory.close();
      return result;

   }

   @Override
   public boolean delete(MeerTocht t) {
      // TODO Auto-generated method stub
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      em.remove(t);
      em.close();
      factory.close();
      return true;

   }

   @Override
   public boolean deleteByOID(Integer OID) {
      // TODO Auto-generated method stub
      Optional<MeerTocht> tochtinsysteem = this.findByOID(OID);
      if (tochtinsysteem.isPresent()) {
         EntityManagerFactory factory =
               Persistence.createEntityManagerFactory("stuga");
         EntityManager em = factory.createEntityManager();

         em.remove(tochtinsysteem.get());
         em.close();
         factory.close();
      }
      return true;

   }

}
