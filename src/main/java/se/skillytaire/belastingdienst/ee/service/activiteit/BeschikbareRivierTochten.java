package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Local;

@Local
public interface BeschikbareRivierTochten {
	BeschikbareRivierTochtenResultTO geefTochten (BeschikbareRivierTochtenTO beschikbareRivierTochtenTO);
}
