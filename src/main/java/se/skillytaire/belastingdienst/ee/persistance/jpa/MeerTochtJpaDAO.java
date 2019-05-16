package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;

public class MeerTochtJpaDAO implements MeerTochtDAO {
	private final static MeerTochtJpaDAO dao = new MeerTochtJpaDAO();
	private EntityManager em;
	// Optional<MeerTocht> find(double prijs, Periode reserveringsPeriode);

	private MeerTochtJpaDAO() {
	}

	public static MeerTochtJpaDAO getDAO() {

		return MeerTochtJpaDAO.dao;
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

	public Optional<MeerTocht> findByBoot(Boot boot) {
		Optional<MeerTocht> result = Optional.ofNullable(null);
		if(boot.isPersistant()) {
			TypedQuery<MeerTocht> query = em.createNamedQuery(MeerTocht.FIND_BY_BOOT_OID, MeerTocht.class);
			query.setParameter("boot_oid", boot.getOid());
			result = query.getResultList().stream().findFirst();
		}
		return result;

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

	public static MeerTochtJpaDAO getInstance() {
      return dao;
   }

   public void setEntityManager(EntityManager entityManager) {
      this.em = entityManager;
   }

}
