package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Account;

public interface AccountDAO extends DAO<Account> {
	Optional<Account> find(String username);
}