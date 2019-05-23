package se.skillytaire.belastingdienst.ee.service.account;

import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class NieuwAccountResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public NieuwAccountResultTO(Integer oid) {
		super(oid);
	}

	public NieuwAccountResultTO() {
		super(ResultTO.ACCOUNT_NIET_AANGEMAAKT);
	}

}