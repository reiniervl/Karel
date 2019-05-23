package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Local;

import se.skillytaire.belastingdienst.ee.common.QRCode;

@Local
public interface StartRivierTocht {
	StartRivierTochtResultTO start(QRCode code);
}