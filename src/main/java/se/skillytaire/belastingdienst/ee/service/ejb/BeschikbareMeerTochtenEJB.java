package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochten;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochtenResultTO;
import se.skillytaire.belastingdienst.ee.service.activiteit.BeschikbareMeerTochtenTO;

@Stateless
public class BeschikbareMeerTochtenEJB implements BeschikbareMeerTochten {

	@Singleton
	MeerTochtDAO meerTochtDAO;

	@Override
	public BeschikbareMeerTochtenResultTO geefTochten(BeschikbareMeerTochtenTO beschikbareMeerTO) {
		BeschikbareMeerTochtenResultTO result;
		Optional<List<MeerTocht>> tochten = meerTochtDAO.findBeschikbareTochten();
		
		if(tochten.isPresent()) {
			result = new BeschikbareMeerTochtenResultTO(tochten.get());
		} else {
			result = new BeschikbareMeerTochtenResultTO();
		}
		return result;
	}

}
