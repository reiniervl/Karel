package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;

public class RivierTochtJpaDAO implements RivierTochtDAO {
   private static final RivierTochtJpaDAO instance = new RivierTochtJpaDAO();
   private EntityManager em;

   private RivierTochtJpaDAO() {
   }

   public void setEntityManager(final EntityManager entityManager) {
      this.em = entityManager;
   }

   public static RivierTochtJpaDAO getInstance() {
      return RivierTochtJpaDAO.instance;
   }

   @Override
   public void add(final RivierTocht rivierTocht) {
      this.em.persist(rivierTocht);
   }

   @Override
   public Optional<RivierTocht> findByOID(final Integer OID) {
      RivierTocht gevondenRivierTocht = this.em.find(RivierTocht.class, OID);
      return Optional.ofNullable(gevondenRivierTocht);
   }

   @Override
   public RivierTocht update(final RivierTocht rivierTocht) {
      this.em.merge(rivierTocht);
      return this.em.merge(rivierTocht);
   }

   @Override
   public boolean delete(final RivierTocht rivierTocht) {
      this.em.remove(rivierTocht);
      return true;
   }

   @Override
   public boolean deleteByOID(final Integer OID) {
      Query namedQuery = this.em.createNamedQuery(RivierTocht.DELETE_BY_OID);
      namedQuery.setParameter("oid", OID);
      int result = namedQuery.executeUpdate();
      return result != 0;
   }
}
