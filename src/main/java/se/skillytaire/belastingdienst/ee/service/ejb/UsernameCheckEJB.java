package se.skillytaire.belastingdienst.ee.service.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.account.UsernameCheck;

@Stateless
public class UsernameCheckEJB implements UsernameCheck {
	@Inject
	private KlantJpaDAO dao;
	
	public UsernameCheckEJB(KlantJpaDAO dao) {
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