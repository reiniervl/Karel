package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.entity.Tocht;
import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.entity.ReserveringBuilder;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;
import se.skillytaire.belastingdienst.ee.persistance.ReserveringDAO;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKResultTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountAKTO;
import se.skillytaire.belastingdienst.ee.service.account.AccountExistsService;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReservering;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringResultTO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MaakNieuweReserveringEJB implements NieuweReservering {
	@Singleton
	ReserveringDAO reserveringDAO;
	@Inject
	AccountExistsService accountExistsService;
	@Singleton
	MeerTochtDAO meerTochtDAO;
	@Singleton
	RivierTochtDAO rivierTochtDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public NieuweReserveringResultTO reserveren(NieuweReserveringTO reserveringTO) {

		String usernameAccount = reserveringTO.getUsernameAccount();
		String usernameVerhuurder = reserveringTO.getUsernameVerhuuder();
		Integer activiteitOID = reserveringTO.getOidActiviteit();
		AccountAKTO accountAKTO = new AccountAKTO(usernameAccount, usernameVerhuurder);
		AccountAKResultTO klantAccount = accountExistsService.exists(accountAKTO);
		
		NieuweReserveringResultTO result;
		
		if (klantAccount.isSuccessful() && klantAccount.getResult().isPresent()) {
			Optional<Tocht<?>> activiteit = this.findTocht(activiteitOID);
			// Reservering reservering = ReserveringBuilder.builder()
			// 											.withAccount(klantAccount.getResult().get())
			// 											.withVerloopDatum(MaakNieuweReserveringEJB.getVerloopDatum())
			// 											.build();
			// reservering.add(activiteit.get());
			Reservering reservering = null;
			try {
				reserveringDAO.add(reservering);
				result = new NieuweReserveringResultTO(reservering.getOid());
			} catch (RuntimeException e) {
				result = new NieuweReserveringResultTO();
				throw e;
			}
		} else {
			AccountAKResultTO accountResult = new AccountAKResultTO();
			result = new NieuweReserveringResultTO(accountResult);
		}
		return result;
	}
	
	private Optional <Tocht<?>> findTocht(Integer activiteitOID){
		Tocht<?> activiteit;
		Optional<MeerTocht> meerTocht = meerTochtDAO.findByOID(activiteitOID);
		
		if (meerTocht.isPresent()) {
			activiteit = meerTocht.get();
		} else {
		Optional<RivierTocht> rivierTocht = rivierTochtDAO.findByOID(activiteitOID);
			activiteit = rivierTocht.get();
		}
		return Optional.ofNullable(activiteit);
	}
	
	private static Periode getVerloopDatum() {
		return Periode.oneDay();
	}

}
