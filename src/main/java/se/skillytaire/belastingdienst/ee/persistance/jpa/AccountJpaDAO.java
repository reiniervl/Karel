package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKTO;

public class AccountJpaDAO extends AbstractJPADAO<Account> implements AccountDAO {
	public AccountJpaDAO() {
		super(Account.class);
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