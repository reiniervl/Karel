package se.skillytaire.belastingdienst.ee.service.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.entity.ReserveringBuilder;
import se.skillytaire.belastingdienst.ee.persistance.ReserveringDAO;
import se.skillytaire.belastingdienst.ee.service.NieuweReservering;
import se.skillytaire.belastingdienst.ee.service.NieuweReserveringResultTO;
import se.skillytaire.belastingdienst.ee.service.NieuweReserveringTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MaakNieuweReserveringEJB implements NieuweReservering {
	@Singleton
	ReserveringDAO dao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public NieuweReserveringResultTO doIt(NieuweReserveringTO reserveringTO) {
		NieuweReserveringResultTO result;
		Reservering reservering = ReserveringBuilder.builder()
													.withAccount(reserveringTO.getAccount())
													.withVerloopDatum(reserveringTO.getVerloopDatum())
													.withReserveringsDatum(reserveringTO.getReserveringsDatum())
													.build();
		try {
			dao.add(reservering);
			result = new NieuweReserveringResultTO(reservering.getOid());
		} catch(RuntimeException e) {
			result = new NieuweReserveringResultTO();
			throw e;
		}
		return result;
	}
}
