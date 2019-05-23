package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Local;

@Local
public interface UsernameCheck {
	boolean isBeschikbaar(String username);
}