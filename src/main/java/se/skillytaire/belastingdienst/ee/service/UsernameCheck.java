package se.skillytaire.belastingdienst.ee.service;

import javax.ejb.Stateless;

@Stateless
public class UsernameCheck implements UsernameCheckRemote {
	@Override
	public boolean isUniek(String username) {
		return false;
	}
}