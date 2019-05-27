package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareRivierTochten;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareRivierTochtenResultTO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareRivierTochtenTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class BeschikbareRivierTochtenEJB implements BeschikbareRivierTochten {
	@Inject
	RivierTochtDAO rivierTochtDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public BeschikbareRivierTochtenResultTO geefTochten(BeschikbareRivierTochtenTO beschikbareRivierTochtenTO) {
		BeschikbareRivierTochtenResultTO result;
		Optional<List<RivierTocht>> tochtOptional;
		if(rivierTochtDAO != null) tochtOptional = rivierTochtDAO.findBeschikbareTochten();
		else tochtOptional = Optional.ofNullable(null);
		
		if(tochtOptional.isPresent()) {
			result = new BeschikbareRivierTochtenResultTO(tochtOptional.get());
		} else {
			result = new BeschikbareRivierTochtenResultTO();
		}

		return result;
	}
}