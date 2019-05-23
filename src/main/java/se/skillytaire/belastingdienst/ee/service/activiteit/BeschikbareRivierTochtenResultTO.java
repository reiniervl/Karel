package se.skillytaire.belastingdienst.ee.service.activiteit;

import java.util.List;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class BeschikbareRivierTochtenResultTO extends ResultTO<List<RivierTocht>>{
	private static final long serialVersionUID = 1L;

	public BeschikbareRivierTochtenResultTO() {
		super(ResultTO.BESCHIKBARE_RIVIERTOCHTEN_NIET_GEVONDEN);
	}
	public BeschikbareRivierTochtenResultTO(List<RivierTocht> tochten) {
		super(tochten);
	}
}
