package se.skillytaire.belastingdienst.ee.service;

public class StartTochtResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public StartTochtResultTO(Integer meerTochtOID) {
		super(meerTochtOID);
	}

	public StartTochtResultTO() {
		super(ResultTO.TOCHT_NIET_GESTART);
	}
}