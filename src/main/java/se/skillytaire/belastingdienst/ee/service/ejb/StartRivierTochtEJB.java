package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.common.QRCode;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.BootDAO;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;
import se.skillytaire.belastingdienst.ee.service.activiteit.StartRivierTocht;
import se.skillytaire.belastingdienst.ee.service.activiteit.StartRivierTochtResultTO;

@Stateless
public class StartRivierTochtEJB implements StartRivierTocht {

	@Singleton
	BootDAO bootDAO;

	@Singleton
	RivierTochtDAO rivierTochtDAO;

	public StartRivierTochtEJB() {}
	/*
	 * public StartRivierTochtResultTO start(QRCode code) { StartRivierTochtResultTO
	 * result = new StartRivierTochtResultTO(); Optional<Boot> bootRes =
	 * bootDAO.find(code); if(bootRes.isPresent()) { Optional<RivierTocht> tochtRes
	 * = rivierTochtDAO.findByBoot(bootRes.get()); if(tochtRes.isPresent()) {
	 * RivierTocht tocht = tochtRes.get(); tocht.start(); tocht =
	 * rivierTochtDAO.update(tocht); result = new
	 * StartRivierTochtResultTO(tocht.getOid()); } } return result; }
	 */

	@Override
	public StartRivierTochtResultTO start(QRCode code) {
		// TODO Auto-generated method stub
		return null;
	}
}