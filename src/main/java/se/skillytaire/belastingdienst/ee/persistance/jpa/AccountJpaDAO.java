package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;

public class AccountJpaDAO implements AccountDAO {
	private final static AccountJpaDAO dao = new AccountJpaDAO();
	private EntityManager em;

	private AccountJpaDAO() {
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public static AccountJpaDAO getInstance() {
		return AccountJpaDAO.dao;
	}

	@Override
	public void add(final Account Account) {
		em.persist(Account);
	}

	@Override
	public Optional<Account> findByOID(final Integer OID) {
		return Optional.ofNullable(this.em.find(Account.class, OID));
	}

	@Override
	public Account update(final Account Account) {
		return this.em.merge(Account);
	}

	@Override
	public boolean delete(final Account Account) {
		em.remove(Account);
		return true;
	}

	@Override
	public boolean deleteByOID(final Integer OID) {
		boolean deleted = false;
		Optional<Account> gevondenAccount = this.findByOID(OID);
		if (gevondenAccount.isPresent()) {
			em.remove(gevondenAccount.get());
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Optional<Account> find(String usernameklant, String usernameverhuurder) {
		TypedQuery<Account> namedQuery = this.em.createNamedQuery(Account.FIND_BY_UC, Account.class);
		namedQuery.setParameter("usernameklant", usernameklant);
		namedQuery.setParameter("usernameverhuurder", usernameverhuurder);
		Optional<Account> result = namedQuery.getResultList().stream().findFirst();
		return result;
	}
}