package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;

import se.skillytaire.belastingdienst.ee.common.QRCode;

@Stateless
public class StartMeertocht implements StartMeertochtRemote {

	public StartTochtResultTO start(QRCode code) {
		StartTochtResultTO result = new StartTochtResultTO();
		return result;
	}
}