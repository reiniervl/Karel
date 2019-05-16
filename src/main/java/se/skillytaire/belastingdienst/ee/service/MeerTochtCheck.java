package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.belastingdienst.ee.persistance.DAO;

@Stateless
public class MeerTochtCheck implements MeerTochtCheckRemote {

	@Singleton
	private DAO<MeerTocht> dao;

	public MeerTochtCheck(DAO<MeerTocht> dao) {
		super();
		this.dao = dao;
	}

	public MeerTochtCheck() {
		super();
	}

	@Override
	public boolean isBeschikbaar(Boot boot, double prijs, Periode reserveringsPeriode) {
		return false;
	}
}
