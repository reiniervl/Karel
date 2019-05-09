package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class BootJpaDAO implements DAO<Boot> {
	private static final BootJpaDAO instance = new BootJpaDAO();
	private EntityManager em;

	private BootJpaDAO() {
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}

	public static BootJpaDAO getInstance() {
		return BootJpaDAO.instance;
	}

	@Override
	public void add(final Boot boot) {
		this.em.persist(boot);
	}

	@Override
	public Optional<Boot> findByOID(final Integer OID) {
		Boot gevondenBoot = this.em.find(Boot.class, OID);
		return Optional.ofNullable(gevondenBoot);
	}

	@Override
	public Boot update(final Boot boot) {
		return this.em.merge(boot);
	}

	@Override
	public boolean delete(final Boot boot) {
		this.em.remove(boot);
		return true;
	}

	@Override
	public boolean deleteByOID(final Integer OID) {
		Query namedQuery = this.em.createNamedQuery(Boot.DELETE_BY_OID);
		namedQuery.setParameter("oid", OID);
		int result = namedQuery.executeUpdate();
		return result != 0;
	}

}
