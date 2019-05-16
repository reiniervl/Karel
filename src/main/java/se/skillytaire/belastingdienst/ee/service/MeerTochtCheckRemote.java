package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Remote;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.Periode;

@Remote
public interface MeerTochtCheckRemote {
	boolean isBeschikbaar(Boot boot, double prijs, Periode reserveringsPeriode) ;
}

