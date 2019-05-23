package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
@Default
@ApplicationScoped
public class KlantJpaDAO implements KlantDAO {
	 private final static KlantJpaDAO dao = new KlantJpaDAO();
		@PersistenceContext
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

	@Override
	public Optional<Klant> find(String username) {
    TypedQuery<Klant> namedQuery = this.em.createNamedQuery(Klant.FIND_BY_USERNAME,
		Klant.class);
    namedQuery.setParameter("username", username);
    Optional<Klant> result = namedQuery.getResultList().stream().findFirst();
    return result;
	}
}
