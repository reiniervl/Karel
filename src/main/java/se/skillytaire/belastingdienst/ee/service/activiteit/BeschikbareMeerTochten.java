package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Remote;

@Remote
public interface BeschikbareMeerTochten {
	BeschikbareMeerTochtenResultTO geefTochten (BeschikbareMeerTochtenTO beschikbareMeerTO);
	
}
