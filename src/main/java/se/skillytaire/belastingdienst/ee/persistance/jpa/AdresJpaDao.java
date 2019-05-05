package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.AdresDao;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class AdresJpaDao implements AdresDao{
   private static final AdresJpaDao instance = new AdresJpaDao();
   private EntityManager em;
   
   private AdresJpaDao() {
   }
   
   public void setEntityManager(EntityManager entityManager ) {
      this.em = entityManager;
   }
   public static AdresJpaDao getInstance() {
      return instance;
   }
   @Override
   public void add(Adres adres) {
      em.persist(adres);
   }

   @Override
   public Optional<Adres> findByOID(Integer OID) {
      Adres gevondenKlant = em.find(Adres.class, OID);
      return Optional.ofNullable(gevondenKlant);
   }

   @Override
   public Adres update(Adres adres) {
      return em.merge(adres);
   }

   @Override
   public boolean delete(Adres adres) {
      em.remove(adres);
      return true;
   }

   @Override
   public boolean deleteByOID(Integer oid) {
      Query namedQuery = em.createNamedQuery(Adres.DELETE_BY_OID);
      namedQuery.setParameter("oid", oid);
      int result = namedQuery.executeUpdate();
      return result != 0;
      
   }

   public Optional<Adres> find(GPSCoordinaat coordinate) {
      TypedQuery<Adres> namedQuery = em.createNamedQuery(Adres.FIND_BY_GPS, Adres.class);
      namedQuery.setParameter(EmbeddableGPSCoordinaat.PROPERTY_LAT, coordinate.getLatitude());
      namedQuery.setParameter(EmbeddableGPSCoordinaat.PROPERTY_LONG, coordinate.getLongtitude());
      Optional<Adres> result = namedQuery.getResultStream().findFirst();
      return result;
   }

}
