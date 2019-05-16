package se.skillytaire.belastingdienst.ee.service;

public class StartMeerTochtResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public StartMeerTochtResultTO(Integer tochtOID) {
		super(tochtOID);
	}

	public StartMeerTochtResultTO() {
		super(ResultTO.TOCHT_NIET_GESTART);
	}
}