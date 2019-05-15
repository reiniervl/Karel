package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.AccountBuilder;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
import se.skillytaire.belastingdienst.ee.service.NieuwAccountResultTO;
import se.skillytaire.belastingdienst.ee.service.RegistreerNieuwAccount;
import se.skillytaire.belastingdienst.ee.service.ResultTO;

@Stateless
public class RegistreerNieuwAccountEJB implements RegistreerNieuwAccount {
	@Singleton
	AccountDAO accountDAO;

	@Singleton
	KlantDAO klantDAO;

	@Transactional
	public NieuwAccountResultTO registreer(Integer klantOid) {
		NieuwAccountResultTO result;

		Optional<Klant> klant = klantDAO.findByOID(klantOid);
		if(klant.isPresent()) {
			Account account = AccountBuilder.builder().withKlant(klant.get()).build();
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