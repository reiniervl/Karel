package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;

@Default
@ApplicationScoped
public class RivierTochtJpaDAO implements RivierTochtDAO {
	private static final RivierTochtJpaDAO instance = new RivierTochtJpaDAO();
	@PersistenceContext
	private EntityManager em;

	public RivierTochtJpaDAO() {
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}

	/**
	 * Wordt CDI
	 * 
	 * @return
	 */
	@Deprecated
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

	@Override
	public Optional<List<RivierTocht>> findBeschikbareTochten() {
		TypedQuery<RivierTocht> query = this.em.createNamedQuery(RivierTocht.BESCHIKBARE_TOCHTEN, RivierTocht.class);
		List<RivierTocht> result = query.getResultList();
		return Optional.ofNullable(result);
	}
}
