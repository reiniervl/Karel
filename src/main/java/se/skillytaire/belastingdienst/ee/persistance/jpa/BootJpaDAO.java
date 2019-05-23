package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.BootDAO;
@Default
@ApplicationScoped
public class BootJpaDAO implements BootDAO {
	private static final BootJpaDAO instance = new BootJpaDAO();
	@PersistenceContext
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

	@Override
	public Optional<Boot> findBeschikbareBoot(Verhuurder verhuurder) {
		Boot beschikbareBoot = null;
		TypedQuery<Boot> query = this.em.createNamedQuery(Boot.SELECT_BY_ISBESCHIKBAAR, Boot.class);
		query.setParameter("verhuurder", verhuurder.getUserName());
		List<Boot> lijstMetBoten = query.getResultList();
		for (Boot boot : lijstMetBoten) {
			if (boot.isBeschikbaar()) {
				beschikbareBoot = boot;
				break;
			}
		}
		return Optional.ofNullable(beschikbareBoot);
	}

}
