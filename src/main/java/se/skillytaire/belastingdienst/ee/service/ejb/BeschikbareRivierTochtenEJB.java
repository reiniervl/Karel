package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareRivierTochten;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareRivierTochtenResultTO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareRivierTochtenTO;

@Stateless
public class BeschikbareRivierTochtenEJB implements BeschikbareRivierTochten {
	@Singleton
	RivierTochtDAO rivierTochtDAO;

	@Override
	public BeschikbareRivierTochtenResultTO geefTochten(BeschikbareRivierTochtenTO beschikbareRivierTochtenTO) {
		BeschikbareRivierTochtenResultTO result;
		Optional<List<RivierTocht>> tochtOptional = rivierTochtDAO.findBeschikbareTochten();
		
		if(tochtOptional.isPresent()) {
			result = new BeschikbareRivierTochtenResultTO(tochtOptional.get());
		} else {
			result = new BeschikbareRivierTochtenResultTO();
		}

		return result;
	}
}