package se.skillytaire.belastingdienst.ee.service;

public class StartTochtResultTO extends ResultTO<Boolean> {
	private static final long serialVersionUID = 1L;

	public StartTochtResultTO(Boolean gestart) {
		super(gestart);
	}

	public StartTochtResultTO() {
		super(ResultTO.TOCHT_NIET_GESTART);
	}
}