package se.skillytaire.belastingdienst.ee.service;

public abstract class StartTochtResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public StartTochtResultTO(Integer tochtOID) {
		super(tochtOID);
	}

	public StartTochtResultTO() {
		super(ResultTO.TOCHT_NIET_GESTART);
	}
}