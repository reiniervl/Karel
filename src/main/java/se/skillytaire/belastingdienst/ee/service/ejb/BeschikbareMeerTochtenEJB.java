package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtJpaDAO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochten;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochtenResultTO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochtenTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class BeschikbareMeerTochtenEJB implements BeschikbareMeerTochten {

	@Inject
	MeerTochtJpaDAO meerTochtDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public BeschikbareMeerTochtenResultTO geefTochten(BeschikbareMeerTochtenTO beschikbareMeerTO) {
		BeschikbareMeerTochtenResultTO result;

		Optional<List<MeerTocht>> tochten;
		if(meerTochtDAO != null) tochten = meerTochtDAO.findBeschikbareTochten();
		else tochten = Optional.ofNullable(null);
		
		if(tochten.isPresent()) {
			result = new BeschikbareMeerTochtenResultTO(tochten.get());
		} else {
			result = new BeschikbareMeerTochtenResultTO();
		}
		return result;
	}

}
