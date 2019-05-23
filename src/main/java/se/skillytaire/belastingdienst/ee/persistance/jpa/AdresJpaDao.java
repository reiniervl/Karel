package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.persistance.AdresDao;
@Default
@ApplicationScoped
public class AdresJpaDao implements AdresDao {
   private static final AdresJpaDao instance = new AdresJpaDao();
	@PersistenceContext
   private EntityManager em;

   public AdresJpaDao() {
   }

   public void setEntityManager(final EntityManager entityManager) {
      this.em = entityManager;
   }
	/**
	 * Wordt CDI
	 * @return
	 */
	@Deprecated
   public static AdresJpaDao getInstance() {
      return AdresJpaDao.instance;
   }

   @Override
   public void add(final Adres adres) {
      this.em.persist(adres);
   }

   @Override
   public Optional<Adres> findByOID(final Integer OID) {
      Adres gevondenKlant = this.em.find(Adres.class, OID);
      return Optional.ofNullable(gevondenKlant);
   }

   @Override
   public Adres update(final Adres adres) {
      return this.em.merge(adres);
   }

   @Override
   public boolean delete(final Adres adres) {
      this.em.remove(adres);
      return true;
   }

   @Override
   public boolean deleteByOID(final Integer oid) {
      Query namedQuery = this.em.createNamedQuery(Adres.DELETE_BY_OID);
      namedQuery.setParameter("oid", oid);
      int result = namedQuery.executeUpdate();
      return result != 0;

   }

   @Override
   public Optional<Adres> find(final GPSCoordinaat coordinate) {
      TypedQuery<Adres> namedQuery =
            this.em.createNamedQuery(Adres.FIND_BY_GPS, Adres.class);
      namedQuery.setParameter(EmbeddableGPSCoordinaat.PROPERTY_LAT,
            coordinate.getLatitude());
      namedQuery.setParameter(EmbeddableGPSCoordinaat.PROPERTY_LONG,
            coordinate.getLongtitude());
      Optional<Adres> result = namedQuery.getResultList().stream().findFirst();
      return result;
   }

}
