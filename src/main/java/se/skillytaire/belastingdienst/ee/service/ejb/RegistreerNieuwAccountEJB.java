package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.AccountBuilder;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
import se.skillytaire.belastingdienst.ee.service.ResultTO;
import se.skillytaire.belastingdienst.ee.service.account.NieuwAccountResultTO;
import se.skillytaire.belastingdienst.ee.service.account.RegistreerNieuwAccount;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RegistreerNieuwAccountEJB implements RegistreerNieuwAccount {
	@Singleton
	AccountDAO accountDAO;

	@Singleton
	KlantDAO klantDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NieuwAccountResultTO registreer(Integer klantOid) {
		NieuwAccountResultTO result;

		Optional<Klant> klant = klantDAO.findByOID(klantOid);
		if(klant.isPresent()) {
			Account account = AccountBuilder.builder()
				.withVerhuurder(new Verhuurder())
				.withKlant(klant.get())
				.build();
			// Account account = new Account(klant.get());
			try {
				accountDAO.add(account);
				result = new NieuwAccountResultTO(ResultTO.NO_ERROR);
			} catch(RuntimeException e) {
				// FIXME: rollback
				result = new NieuwAccountResultTO();
			}
		} else {
			result = new NieuwAccountResultTO();
		}
		return result;
	}
}