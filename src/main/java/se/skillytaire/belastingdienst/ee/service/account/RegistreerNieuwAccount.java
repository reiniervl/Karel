package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface RegistreerNieuwAccount {
	NieuwAccountResultTO registreer(Integer klantOid);
}