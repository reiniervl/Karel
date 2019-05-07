package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class MeerTochtDAO implements DAO<MeerTocht> {
   private final static MeerTochtDAO dao = new MeerTochtDAO();
   private EntityManager em;

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
   public boolean delete(MeerTocht t) {
      em.remove(t);
      return true;
   }

   @Override
   public boolean deleteByOID(Integer OID) {
      Optional<MeerTocht> tochtinsysteem = this.findByOID(OID);
      if (tochtinsysteem.isPresent()) {
         em.remove(tochtinsysteem.get());
      }
      return true;
   }

   public static MeerTochtDAO getInstance() {
      return dao;
   }

   public void setEntityManager(EntityManager entityManager) {
      this.em = entityManager;
   }

}
