package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
import se.skillytaire.belastingdienst.ee.service.account.PasswordCheck;

@Stateless
public class PasswordCheckEJB implements PasswordCheck {
	@Singleton
	private KlantDAO dao;
	
	public PasswordCheckEJB(KlantDAO dao) {
		super();
		this.dao = dao;
	}
	
	public PasswordCheckEJB() {
		super();
	}

	@Override
	public boolean isValide(String username, String password) {
		Optional<Klant> klant = dao.find(username);
		String passwordCheck = klant.get().getPassword();
		return passwordCheck.equals(password);
	}
}