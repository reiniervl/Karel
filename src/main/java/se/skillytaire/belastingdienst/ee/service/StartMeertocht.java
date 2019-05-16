package se.skillytaire.belastingdienst.ee.service;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.common.QRCode;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.BootDAO;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;

@Stateless
public class StartMeertocht implements StartMeertochtRemote {

	@Singleton
	BootDAO bootDAO;

	@Singleton
	MeerTochtDAO meerTochtDAO;

	public StartMeertocht() {}

	public StartTochtResultTO start(QRCode code) {
		StartTochtResultTO result = new StartTochtResultTO();
		Optional<Boot> bootRes = bootDAO.find(code);
		if(bootRes.isPresent()) {
			Optional<MeerTocht> tochtRes = meerTochtDAO.findByBoot(bootRes.get());
			if(tochtRes.isPresent()) {
				MeerTocht tocht = tochtRes.get();
				tocht.start();
				tocht = meerTochtDAO.update(tocht);
				result = new StartTochtResultTO(tocht.getOid());
			}
		}
		return result;
	}
}