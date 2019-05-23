package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;
@Default
@ApplicationScoped
public class MeerTochtJpaDAO implements MeerTochtDAO {
	private final static MeerTochtJpaDAO dao = new MeerTochtJpaDAO();
	@PersistenceContext
	private EntityManager em;

	public MeerTochtJpaDAO() {
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
	/**
	 * Wordt CDI
	 * @return
	 */
	@Deprecated
	public static MeerTochtJpaDAO getInstance() {
		return dao;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	@Override
	public Optional<List<MeerTocht>> findBeschikbareTochten() {
		TypedQuery<MeerTocht> query = this.em.createNamedQuery(MeerTocht.BESCHIKBARE_TOCHTEN, MeerTocht.class);
		List<MeerTocht> result = query.getResultList();
		return Optional.ofNullable(result);
	}

}
