package se.skillytaire.belastingdienst.ee.service;

public class NieuweReserveringResultTO extends ResultTO<Integer> {
	private static final long serialVersionUID = 1L;

	public NieuweReserveringResultTO(Integer oid) {
		super(oid);
	
	}

	public NieuweReserveringResultTO() {
		super(ResultTO.Nieuwe_Klant_Exists);
	}
}
