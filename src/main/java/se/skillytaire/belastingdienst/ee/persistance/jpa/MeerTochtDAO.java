package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class MeerTochtDAO implements DAO<MeerTocht> {
   private final static MeerTochtDAO dao = new MeerTochtDAO();
   private EntityManager em;
 //  Optional<MeerTocht> find(double prijs, Periode reserveringsPeriode);

   
   private MeerTochtDAO() {
   }

   public static MeerTochtDAO getDAO() {

      return MeerTochtDAO.dao;
   }

   @Override
   public void add(MeerTocht t) {
      this.em.persist(t);
   }

   @Override
   public Optional<MeerTocht> findByOID(Integer OID) {
      MeerTocht tochtinsysteem = em.find(MeerTocht.class, OID);
      return Optional.ofNullable(tochtinsysteem);
   }

   @Override
   public MeerTocht update(MeerTocht eenMeerTocht) {
      return this.em.merge(eenMeerTocht);
   }

   @Override
   public boolean deleteByOID(Integer OID) {
	      Query namedQuery = this.em.createNamedQuery(MeerTocht.DELETE_BY_OID);
	      namedQuery.setParameter("oid", OID);
	      int result = namedQuery.executeUpdate();
	      return result != 0;
   }

   public static MeerTochtDAO getInstance() {
      return dao;
   }

   public void setEntityManager(EntityManager entityManager) {
      this.em = entityManager;
   }

}
