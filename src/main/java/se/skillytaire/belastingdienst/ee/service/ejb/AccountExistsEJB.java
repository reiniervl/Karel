package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.AccountBuilder;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.VerhuurderJpaDAO;
import se.skillytaire.belastingdienst.ee.service.ResultTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKResultTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountExistsService;

/**
 * Account wordt aangemaakt als het nog niet bestaat
 */
@Stateless
public class AccountExistsEJB implements AccountExistsService {
	@Inject	public AccountJpaDAO accountDAO;
	@Inject private VerhuurderJpaDAO verhuurderDAO;
	@Inject private KlantJpaDAO klantDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public AccountAKResultTO exists(AccountAKTO accountAKTO) {
		AccountAKResultTO result;
		Optional<Account> gevondenAccount = accountDAO.find(accountAKTO);

		if (gevondenAccount.isPresent()) {
			result = new AccountAKResultTO(gevondenAccount.get());
		} else {
			Optional<Verhuurder> verhuurder = verhuurderDAO.find(accountAKTO.getUsernameVerhuurder());
			Optional<Klant> klant = klantDAO.find(accountAKTO.getUsernameKlant());

			if(!verhuurder.isPresent()) {
				result = new AccountAKResultTO(ResultTO.VERHUURDER_NIET_GEVONDEN);
			} else if(!klant.isPresent()) {
				result = new AccountAKResultTO(ResultTO.KLANT_NIET_GEVONDEN);
			} else {
				Account account = AccountBuilder.builder()
					.withVerhuurder(verhuurder.get())
					.withKlant(klant.get())
					.build();

				accountDAO.add(account);
				result = new AccountAKResultTO(account);
			}
		}
		return result;
	}
}