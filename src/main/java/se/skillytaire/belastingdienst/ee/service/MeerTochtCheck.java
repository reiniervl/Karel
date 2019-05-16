package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtDAO;

@Stateless
public class MeerTochtCheck implements MeerTochtCheckRemote {

	public MeerTochtCheck(MeerTochtDAO dao) {
		super();
		this.dao = dao;
	}

	public MeerTochtCheck() {
		super();
	}

	@Singleton
	private MeerTochtDAO dao;
	@Override
	public boolean isBeschikbaar(Boot boot, double prijs, Periode reserveringsPeriode) {
		return false;
				}
}
