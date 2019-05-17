package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Remote;

import se.skillytaire.belastingdienst.ee.common.QRCode;

// @Remote
public interface StartMeertochtRemote {
	StartMeerTochtResultTO start(QRCode code);
}