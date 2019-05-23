package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKTO;

public interface AccountDAO extends DAO<Account> {
	Optional<Account> find(AccountAKTO accountAKTO);
}