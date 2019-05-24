package se.skillytaire.belastingdienst.ee.service.reservering;

import javax.ejb.Local;

@Local
public interface NieuweBoeking {
	NieuweBoekingResultTO boeken(Integer reserveringOid);
}
