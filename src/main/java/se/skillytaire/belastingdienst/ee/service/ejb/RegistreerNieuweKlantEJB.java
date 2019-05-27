package se.skillytaire.belastingdienst.ee.service.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.KlantBuilder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantResultTO;
import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantTO;
import se.skillytaire.belastingdienst.ee.service.account.RegistreerNieuweKlant;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RegistreerNieuweKlantEJB implements RegistreerNieuweKlant {
	@Inject
	KlantJpaDAO dao;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
			throw e;
		}
		return result;
	}
}