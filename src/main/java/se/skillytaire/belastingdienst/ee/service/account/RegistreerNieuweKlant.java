package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Local;

@Local
public interface RegistreerNieuweKlant {
	NieuweKlantResultTO doIt(NieuweKlantTO klantTO);
}