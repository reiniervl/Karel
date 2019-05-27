package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.Boeking;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.BoekingDAO;
@Default
@ApplicationScoped
public class BoekingJpaDAO extends AbstractJPADAO<Boeking> implements BoekingDAO {
	protected BoekingJpaDAO(Class<Boeking> boeking) {
		super(Boeking.class);
	}

	private static final BoekingJpaDAO instance = new BoekingJpaDAO(Boeking.class);
	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}
	/**
	 * Wordt CDI
	 * @return
	 */
	@Deprecated
	public static BoekingJpaDAO getInstance() {
		return BoekingJpaDAO.instance;
	}

	@Override
	public void add(final Boeking boeking) {
		this.em.persist(boeking);
	}

	@Override
	public Optional<Boeking> findByOID(final Integer OID) {
		Boeking gevondenBoeking = this.em.find(Boeking.class, OID);
		return Optional.ofNullable(gevondenBoeking);
	}

	@Override
	public Boeking update(final Boeking boeking) {
		return this.em.merge(boeking);
	}

	@Override
	public boolean delete(final Boeking boeking) {
		this.em.remove(boeking);
		return true;
	}

	@Override
	public boolean deleteByOID(final Integer OID) {
		Query namedQuery = this.em.createNamedQuery(Boeking.DELETE_BY_OID);
		namedQuery.setParameter("oid", OID);
		int result = namedQuery.executeUpdate();
		return result != 0;
	}
}
