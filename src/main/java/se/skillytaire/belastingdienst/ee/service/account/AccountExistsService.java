package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Local;

@Local
public interface AccountExistsService {
	AccountAKResultTO exists (AccountAKTO accountAKTO);
}