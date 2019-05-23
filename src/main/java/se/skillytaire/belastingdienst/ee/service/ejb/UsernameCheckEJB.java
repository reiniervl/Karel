package se.skillytaire.belastingdienst.ee.service.ejb;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
import se.skillytaire.belastingdienst.ee.service.account.UsernameCheck;

@Stateless
public class UsernameCheckEJB implements UsernameCheck {
	@Singleton
	private KlantDAO dao;
	
	public UsernameCheckEJB(KlantDAO dao) {
		super();
		this.dao = dao;
	}
	
	public UsernameCheckEJB() {
		super();
	}

	@Override
	public boolean isBeschikbaar(String username) {
		return !dao.find(username).isPresent();
	}
}