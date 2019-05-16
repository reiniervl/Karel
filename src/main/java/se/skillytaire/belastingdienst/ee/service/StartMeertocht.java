package se.skillytaire.belastingdienst.ee.service;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.MeerTochtDAO;

@Stateless
public class StartMeertocht implements StartMeertochtRemote {

	@Singleton
	MeerTochtDAO meerTochtDAO;

	public StartTochtResultTO start(Integer meerTochtOID) {
		StartTochtResultTO result;
		try {
			Optional<MeerTocht> meerTocht = meerTochtDAO.findByOID(meerTochtOID);
			if(meerTocht.isPresent()) {
				meerTocht.get().start();
				result = new StartTochtResultTO(meerTocht.get().getActuelePeriode().isGestart());
			} else {
				result = new StartTochtResultTO();
			}
		} catch (RuntimeException e) {
			//TODO: rollback
				result = new StartTochtResultTO();
		}
		return result;
	}
}