package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;

@Stateless
public class UsernameCheck implements UsernameCheckRemote {
	@Singleton
	private KlantDAO dao;
	
	public UsernameCheck(KlantDAO dao) {
		super();
		this.dao = dao;
	}
	
	public UsernameCheck() {
		super();
	}

	@Override
	public boolean isBeschikbaar(String username) {
		return !dao.find(username).isPresent();
	}
}