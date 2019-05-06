package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class KlantDAO implements DAO<Klant> {
   private final static KlantDAO dao = new KlantDAO();

   private KlantDAO() {
   }

   public static KlantDAO getDAO() {

      return KlantDAO.dao;
   }

   @Override
   public void add(final Klant klant) {
      EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      em.persist(klant);

      em.close();
      factory.close();
   }

   @Override
   public Optional<Klant> findByOID(final Integer OID) {
      EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      Klant gevondenKlant = em.find(Klant.class, OID);
      Optional<Klant> optioneleKlant = Optional.ofNullable(gevondenKlant);

      em.close();
      factory.close();

      return optioneleKlant;
   }

   @Override
   public Klant update(final Klant klant) {
      EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      Klant result = em.merge(klant);

      em.close();
      factory.close();
      return result;
   }

   @Override
   public boolean delete(final Klant klant) {
      EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("stuga");
      EntityManager em = factory.createEntityManager();

      em.remove(klant);

      em.close();
      factory.close();
      return true;
   }

   @Override
   public boolean deleteByOID(final Integer OID) {
      Optional<Klant> gevondenKlant = this.findByOID(OID);
      if (gevondenKlant.isPresent()) {
         EntityManagerFactory factory = Persistence
               .createEntityManagerFactory("stuga");
         EntityManager em = factory.createEntityManager();

         em.remove(gevondenKlant.get());

         em.close();
         factory.close();
      }
      return true;
   }

}
