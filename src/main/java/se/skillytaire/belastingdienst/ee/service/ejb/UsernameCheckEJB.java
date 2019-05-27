package se.skillytaire.belastingdienst.ee.service.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.service.account.UsernameCheck;

@Default
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsernameCheckEJB implements UsernameCheck {
	@Inject
	private KlantJpaDAO dao;
	
	public UsernameCheckEJB(KlantJpaDAO dao) {
		this.dao = dao;
	}
	
	public UsernameCheckEJB() {
		super();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isBeschikbaar(String username) {
		return !dao.find(username).isPresent();
	}
}