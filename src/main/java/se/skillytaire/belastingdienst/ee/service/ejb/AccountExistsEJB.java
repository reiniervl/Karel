package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKResultTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountExistsService;

@Stateless
public class AccountExistsEJB implements AccountExistsService {
	@Inject
	public AccountJpaDAO dao;

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public AccountAKResultTO exists(AccountAKTO accountAKTO) {
		AccountAKResultTO result;
		Optional<Account> gevondenAccount = dao.find(accountAKTO);
		if (gevondenAccount.isPresent()) {
			result = new AccountAKResultTO(gevondenAccount.get());
		} else {
			result = new AccountAKResultTO();
		}
		return result;
	}
}