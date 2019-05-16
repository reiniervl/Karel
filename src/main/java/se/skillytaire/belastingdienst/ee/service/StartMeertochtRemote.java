package se.skillytaire.belastingdienst.ee.service;

import se.skillytaire.belastingdienst.ee.common.QRCode;

public interface StartMeertochtRemote {
	StartTochtResultTO start(QRCode code);
}