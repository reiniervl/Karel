package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Remote;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Klant;

@Remote
public interface RegistreerNieuwAccount {
	NieuwAccountResultTO registreer(Integer klantOid);
}