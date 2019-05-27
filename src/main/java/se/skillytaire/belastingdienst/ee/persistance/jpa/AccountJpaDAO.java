package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKTO;

public class AccountJpaDAO extends AbstractJPADAO<Account> implements AccountDAO {
	protected AccountJpaDAO(Class<Account> Account) {
		super(Account.class);
	}

	private final static AccountJpaDAO dao = new AccountJpaDAO(Account.class);
	private EntityManager em;


	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}
	/**
	 * Wordt CDI
	 * @return
	 */
	@Deprecated
	public static AccountJpaDAO getInstance() {
		return AccountJpaDAO.dao;
	}
	
	@Override
	public Optional<Account> findByOID(final Integer OID) {
		return Optional.ofNullable(this.em.find(Account.class, OID));
	}

	@Override
	public Optional<Account> find(AccountAKTO accountAKTO) {
		TypedQuery<Account> namedQuery = this.em.createNamedQuery(Account.FIND_BY_UC, Account.class);
		namedQuery.setParameter("usernameklant", accountAKTO.getUsernameKlant());
		namedQuery.setParameter("usernameverhuurder", accountAKTO.getUsernameVerhuurder());
		Optional<Account> result = namedQuery.getResultList().stream().findFirst();
		return result;
	}
}