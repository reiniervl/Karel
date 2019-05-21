package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.common.QRCode;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;

public interface BootDAO extends DAO<Boot> {
	   Optional<Boot> find (QRCode qrcode);
	   Optional<Boot> findBeschikbareBoot(Verhuurder verhuurder);
}
