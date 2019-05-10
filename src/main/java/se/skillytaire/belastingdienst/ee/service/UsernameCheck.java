package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;

@Stateless
public class UsernameCheck implements UsernameCheckRemote {
	@Override
	public boolean isBeschikbaar(String username) {
		return false;
	}
}