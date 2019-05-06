package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class KlantJpaDAO implements DAO<Klant> {
	 private final static KlantJpaDAO dao = new KlantJpaDAO();
	 private EntityManager em;

   private KlantJpaDAO() {
	 }
	 
	 public void setEntityManager(EntityManager entityManager) {
		 this.em = entityManager;
	 }

   public static KlantJpaDAO getInstance() {
      return KlantJpaDAO.dao;
   }

   @Override
   public void add(final Klant klant) {
      em.persist(klant);
   }

   @Override
   public Optional<Klant> findByOID(final Integer OID) {
			return Optional.ofNullable(this.em.find(Klant.class, OID));
   }

   @Override
   public Klant update(final Klant klant) {
      return this.em.merge(klant);
   }

   @Override
   public boolean delete(final Klant klant) {
      em.remove(klant);
      return true;
   }

   @Override
   public boolean deleteByOID(final Integer OID) {
		 boolean deleted = false;
      Optional<Klant> gevondenKlant = this.findByOID(OID);
      if (gevondenKlant.isPresent()) {
				 em.remove(gevondenKlant.get());
				 deleted = true;
      }
      return deleted;
   }

}
