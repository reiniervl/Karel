package se.skillytaire.belastingdienst.ee.service.reservering;

import javax.ejb.Local;

@Local
public interface NieuweReservering {
	NieuweReserveringResultTO reserveren(NieuweReserveringTO reserveringTO);
}
