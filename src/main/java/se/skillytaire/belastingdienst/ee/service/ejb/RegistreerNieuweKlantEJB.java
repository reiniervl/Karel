package se.skillytaire.belastingdienst.ee.service.ejb;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.KlantBuilder;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
import se.skillytaire.belastingdienst.ee.service.NieuweKlantResultTO;
import se.skillytaire.belastingdienst.ee.service.NieuweKlantTO;
import se.skillytaire.belastingdienst.ee.service.RegistreerNieuweKlant;

@Stateless
public class RegistreerNieuweKlantEJB implements RegistreerNieuweKlant {
	@Singleton
	KlantDAO dao;

	@Transactional
	@Override
	public NieuweKlantResultTO doIt(NieuweKlantTO klantTO) {
		NieuweKlantResultTO result;
		Klant klant = KlantBuilder.builder()
			.withPassword(klantTO.getPassword())
			.withUsername(klantTO.getUsername())
			.withEmail(klantTO.getEmail())
			.build();
		try {
			dao.add(klant);
			result = new NieuweKlantResultTO(klant.getOid());
		} catch(RuntimeException e) {
			// FIXME: rollback transaction
			result = new NieuweKlantResultTO();
		}
		return result;
	}
}