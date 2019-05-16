package se.skillytaire.belastingdienst.ee.helper;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.Periode;

public interface MeerTochtExistsService {
	public abstract boolean exists(Boot boot, double prijs, Periode reserveringsPeriode);

}
