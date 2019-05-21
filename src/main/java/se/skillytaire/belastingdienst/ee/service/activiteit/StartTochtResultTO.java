package se.skillytaire.belastingdienst.ee.service.activiteit;

import se.skillytaire.belastingdienst.ee.service.ResultTO;

public abstract class StartTochtResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public StartTochtResultTO(Integer tochtOID) {
		super(tochtOID);
	}

	public StartTochtResultTO() {
		super(ResultTO.TOCHT_NIET_GESTART);
	}
}