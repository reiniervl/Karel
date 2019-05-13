package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;

@Stateless
public class UsernameCheck implements UsernameCheckRemote {
	
	public UsernameCheck(KlantDAO dao) {
		super();
		this.dao = dao;
	}
	
	public UsernameCheck() {
		super();
	}

	@Singleton
	private KlantDAO dao;
	@Override
	public boolean isBeschikbaar(String username) {
		return !dao.find(username).isPresent();
	}
}