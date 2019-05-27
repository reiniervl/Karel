package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;

@Default
@ApplicationScoped
public class RivierTochtJpaDAO extends AbstractJPADAO<RivierTocht> implements RivierTochtDAO {
	private static final RivierTochtJpaDAO instance = new RivierTochtJpaDAO();

	public RivierTochtJpaDAO() {
		super(RivierTocht.class);
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
	public Optional<List<RivierTocht>> findBeschikbareTochten() {
		TypedQuery<RivierTocht> query = this.em.createNamedQuery(RivierTocht.BESCHIKBARE_TOCHTEN, RivierTocht.class);
		List<RivierTocht> result = query.getResultList();
		return Optional.ofNullable(result);
	}
}
