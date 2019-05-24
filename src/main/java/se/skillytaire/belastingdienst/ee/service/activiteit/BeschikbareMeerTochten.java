package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Local;

@Local
public interface BeschikbareMeerTochten {
	BeschikbareMeerTochtenResultTO geefTochten (BeschikbareMeerTochtenTO beschikbareMeerTO);
	
}
