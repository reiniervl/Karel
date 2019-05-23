package se.skillytaire.belastingdienst.ee.service.reservering;

import javax.ejb.Remote;

@Remote
public interface NieuweReservering {
	NieuweReserveringResultTO reserveren(NieuweReserveringTO reserveringTO);
}
