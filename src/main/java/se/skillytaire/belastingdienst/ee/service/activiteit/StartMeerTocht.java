package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Local;

import se.skillytaire.belastingdienst.ee.common.QRCode;

@Local
public interface StartMeerTocht {
	StartMeerTochtResultTO start(QRCode code);
}