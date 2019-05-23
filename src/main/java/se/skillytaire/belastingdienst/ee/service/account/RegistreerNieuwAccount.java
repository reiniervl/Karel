package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Local;

@Local
public interface RegistreerNieuwAccount {
	NieuwAccountResultTO registreer(Integer klantOid);
}