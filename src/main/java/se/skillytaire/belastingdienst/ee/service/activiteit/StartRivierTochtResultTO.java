package se.skillytaire.belastingdienst.ee.service.activiteit;

import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class StartRivierTochtResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public StartRivierTochtResultTO(Integer tochtOID) {
		super(tochtOID);
	}

	public StartRivierTochtResultTO() {
		super(ResultTO.TOCHT_NIET_GESTART);
	}
}