package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.common.QRCode;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.BootDAO;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;
import se.skillytaire.belastingdienst.ee.service.activiteit.StartMeerTocht;
import se.skillytaire.belastingdienst.ee.service.activiteit.StartMeerTochtResultTO;

@Stateless
public class StartMeerTochtEJB implements StartMeerTocht {

	@Singleton
	BootDAO bootDAO;

	@Singleton
	MeerTochtDAO meerTochtDAO;

	public StartMeerTochtEJB() {}
	/*
	 * public StartMeerTochtResultTO start(QRCode code) { StartMeerTochtResultTO
	 * result = new StartMeerTochtResultTO(); Optional<Boot> bootRes =
	 * bootDAO.find(code); if(bootRes.isPresent()) { Optional<MeerTocht> tochtRes =
	 * meerTochtDAO.findByBoot(bootRes.get()); if(tochtRes.isPresent()) { MeerTocht
	 * tocht = tochtRes.get(); tocht.start(); tocht = meerTochtDAO.update(tocht);
	 * result = new StartMeerTochtResultTO(tocht.getOid()); } } return result; }
	 */

	@Override
	public StartMeerTochtResultTO start(QRCode code) {
		// TODO Auto-generated method stub
		return null;
	}
}