package se.skillytaire.belastingdienst.ee.service.activiteit;

import java.util.List;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class BeschikbareMeerTochtenResultTO extends ResultTO<List<MeerTocht>>{
	private static final long serialVersionUID = 1L;

	public BeschikbareMeerTochtenResultTO() {
		super(ResultTO.BESCHIKBARE_MEERTOCHTEN_NIET_GEVONDEN);
	}
	public BeschikbareMeerTochtenResultTO(List<MeerTocht> tochten) {
		super(tochten);
	}
}
