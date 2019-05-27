package se.skillytaire.belastingdienst.ee.service.ejb;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.account.PasswordCheck;

@Stateless
public class PasswordCheckEJB implements PasswordCheck {
	@Inject
	private KlantJpaDAO dao;
	
	public PasswordCheckEJB(KlantJpaDAO dao) {
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