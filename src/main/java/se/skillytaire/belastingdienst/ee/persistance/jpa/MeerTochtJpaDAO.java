package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;

public class MeerTochtJpaDAO implements MeerTochtDAO {
	private final static MeerTochtJpaDAO dao = new MeerTochtJpaDAO();
	private EntityManager em;

	private MeerTochtJpaDAO() {
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

	public static MeerTochtJpaDAO getInstance() {
      return dao;
   }

   public void setEntityManager(EntityManager entityManager) {
      this.em = entityManager;
   }

}
