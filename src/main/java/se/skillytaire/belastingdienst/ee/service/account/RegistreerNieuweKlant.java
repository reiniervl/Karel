package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Remote;

@Remote
public interface RegistreerNieuweKlant {
	NieuweKlantResultTO doIt(NieuweKlantTO klantTO);
}