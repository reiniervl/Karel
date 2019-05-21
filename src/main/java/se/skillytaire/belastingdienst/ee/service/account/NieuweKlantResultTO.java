package se.skillytaire.belastingdienst.ee.service.account;

import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class NieuweKlantResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;
	
	public NieuweKlantResultTO(Integer oid) {
		super(oid);
	}

	public NieuweKlantResultTO() {
		super(ResultTO.NIEUWE_KLANT_EXISTS);
	}
}