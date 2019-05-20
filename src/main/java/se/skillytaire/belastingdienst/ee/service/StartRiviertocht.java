package se.skillytaire.belastingdienst.ee.service;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.common.QRCode;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.BootDAO;
import se.skillytaire.belastingdienst.ee.persistance.RivierTochtDAO;

@Stateless
public class StartRiviertocht implements StartRivierTochtRemote {

	@Singleton
	BootDAO bootDAO;

	@Singleton
	RivierTochtDAO rivierTochtDAO;

	public StartRiviertocht() {}
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