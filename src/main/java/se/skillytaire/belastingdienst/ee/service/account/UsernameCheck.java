package se.skillytaire.belastingdienst.ee.service.account;

import javax.ejb.Remote;

@Remote
public interface UsernameCheck {
	boolean isBeschikbaar(String username);
}