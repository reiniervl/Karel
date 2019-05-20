package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.Boeking;
import se.skillytaire.belastingdienst.ee.persistance.BoekingDAO;

public class BoekingJpaDAO implements BoekingDAO {
	private static final BoekingJpaDAO instance = new BoekingJpaDAO();
	private EntityManager em;

	private BoekingJpaDAO() {
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}

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
