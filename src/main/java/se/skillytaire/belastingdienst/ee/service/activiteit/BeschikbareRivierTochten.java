package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Remote;

@Remote
public interface BeschikbareRivierTochten {
	BeschikbareRivierTochtenResultTO geefTochten (BeschikbareRivierTochtenTO beschikbareRivierTochtenTO);
}
