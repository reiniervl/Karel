package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Remote;

@Remote
public interface RegistreerNieuwAccount {
	NieuwAccountResultTO registreer(Integer klantOid);
}