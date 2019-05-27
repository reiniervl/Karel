package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.persistance.ReserveringDAO;
@Default
@ApplicationScoped
public class ReserveringJpaDAO extends AbstractJPADAO<Reservering> implements ReserveringDAO {
	private static final ReserveringJpaDAO instance = new ReserveringJpaDAO();

	public ReserveringJpaDAO() {
		super(Reservering.class);
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}
	/**
	 * Wordt CDI
	 * @return
	 */
	@Deprecated
	public static ReserveringJpaDAO getInstance() {
		return ReserveringJpaDAO.instance;
	}

	@Override
	public boolean deleteByOID(final Integer OID) {
		Query namedQuery = this.em.createNamedQuery(Reservering.DELETE_BY_OID);
		namedQuery.setParameter("oid", OID);
		int result = namedQuery.executeUpdate();
		return result != 0;
	}
}
