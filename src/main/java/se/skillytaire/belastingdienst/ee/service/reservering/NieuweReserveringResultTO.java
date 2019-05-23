package se.skillytaire.belastingdienst.ee.service.reservering;

import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class NieuweReserveringResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public NieuweReserveringResultTO(Integer oid) {
		super(oid);
	
	}
	public NieuweReserveringResultTO(ResultTO<?> resultTO) {
		super(resultTO);
	}
	public NieuweReserveringResultTO() {
		super(ResultTO.RESERVERING_NIET_AANGEMAAKT);
	}
}
