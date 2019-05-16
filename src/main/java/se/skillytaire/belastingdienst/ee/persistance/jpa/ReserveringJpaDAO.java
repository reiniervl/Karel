package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.persistance.ReserveringDAO;

public class ReserveringJpaDAO implements ReserveringDAO {
	private static final ReserveringJpaDAO instance = new ReserveringJpaDAO();
	private EntityManager em;

	private ReserveringJpaDAO() {
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}

	public static ReserveringJpaDAO getInstance() {
		return ReserveringJpaDAO.instance;
	}

	@Override
	public void add(final Reservering reservering) {
		this.em.persist(reservering);
	}

	@Override
	public Optional<Reservering> findByOID(final Integer OID) {
		Reservering gevondenReservering = this.em.find(Reservering.class, OID);
		return Optional.ofNullable(gevondenReservering);
	}

	@Override
	public Reservering update(final Reservering reservering) {
		return this.em.merge(reservering);
	}

	@Override
	public boolean delete(final Reservering reservering) {
		this.em.remove(reservering);
		return true;
	}

	@Override
	public boolean deleteByOID(final Integer OID) {
		Query namedQuery = this.em.createNamedQuery(Reservering.DELETE_BY_OID);
		namedQuery.setParameter("oid", OID);
		int result = namedQuery.executeUpdate();
		return result != 0;
	}
}
