package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.persistance.AdresDao;

@Default
@ApplicationScoped
public class AdresJpaDao extends AbstractJPADAO<Adres> implements AdresDao {
   private static final AdresJpaDao instance = new AdresJpaDao();

   public AdresJpaDao() {
		 super(Adres.class);
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
