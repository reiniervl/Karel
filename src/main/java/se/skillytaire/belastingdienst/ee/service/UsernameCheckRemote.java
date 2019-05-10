package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Remote;

@Remote
public interface UsernameCheckRemote {
	boolean isBeschikbaar(String username);
}