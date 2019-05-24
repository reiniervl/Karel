package se.skillytaire.belastingdienst.ee.service.reservering;

import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class NieuweBoekingResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public NieuweBoekingResultTO(Integer oid) {
		super(oid);
	
	}
	public NieuweBoekingResultTO() {
		super(ResultTO.BOEKING_NIET_GELUKT);
	}
}
