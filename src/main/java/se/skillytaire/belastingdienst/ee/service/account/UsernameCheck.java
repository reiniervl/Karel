package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface UsernameCheck {
	boolean isBeschikbaar(String username);
}