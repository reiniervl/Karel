package se.skillytaire.belastingdienst.ee.service.activiteit;

import javax.ejb.Remote;

import se.skillytaire.belastingdienst.ee.common.QRCode;

@Remote
public interface StartRivierTocht {
	StartRivierTochtResultTO start(QRCode code);
}