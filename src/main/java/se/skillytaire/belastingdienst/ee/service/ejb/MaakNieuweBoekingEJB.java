package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.Boeking;
import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.persistance.BoekingDAO;
import se.skillytaire.belastingdienst.ee.persistance.ReserveringDAO;
import se.skillytaire.belastingdienst.ee.service.ResultTO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweBoeking;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweBoekingResultTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MaakNieuweBoekingEJB implements NieuweBoeking {
	@Singleton
	BoekingDAO boekingDAO;

	@Singleton
	ReserveringDAO reserveringDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NieuweBoekingResultTO boeken(Integer reserveringOid) {
		NieuweBoekingResultTO result;

		Optional<Reservering> reservering = reserveringDAO.findByOID(reserveringOid);
		if(reservering.isPresent()) {
			Boeking boeking = new Boeking(reservering.get());
			try {
				boekingDAO.add(boeking);
				result = new NieuweBoekingResultTO(ResultTO.NO_ERROR);
			} catch(RuntimeException e) {
				// FIXME: rollback
				result = new NieuweBoekingResultTO();
			}
		} else {
			result = new NieuweBoekingResultTO();
		}
		return result;
	}
}