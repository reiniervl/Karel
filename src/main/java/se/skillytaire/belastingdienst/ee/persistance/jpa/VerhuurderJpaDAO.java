package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;

import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

public class VerhuurderJpaDAO implements DAO<Verhuurder> {
	private static final VerhuurderJpaDAO instance = new VerhuurderJpaDAO();
	private EntityManager em;

	private VerhuurderJpaDAO() {
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}

	public static VerhuurderJpaDAO getInstance() {
		return VerhuurderJpaDAO.instance;
	}

	@Override
	public void add(final Verhuurder verhuurder) {
		this.em.persist(verhuurder);
	}

	@Override
	public Optional<Verhuurder> findByOID(final Integer OID) {
		Verhuurder gevondenVerhuurder = this.em.find(Verhuurder.class, OID);
		return Optional.ofNullable(gevondenVerhuurder);
	}

	@Override
	public Verhuurder update(final Verhuurder verhuurder) {
		return this.em.merge(verhuurder);
	}

	@Override
	public boolean delete(final Verhuurder verhuurder) {
		this.em.remove(verhuurder);
		return true;
	}

	@Override
	public boolean deleteByOID(final Integer OID) {
		boolean deleted = false;
		 Optional<Verhuurder> gevondenVerhuurder = this.findByOID(OID);
		 if (gevondenVerhuurder.isPresent()) {
				em.remove(gevondenVerhuurder.get());
				deleted = true;
		 }
		 return deleted;
	}

}