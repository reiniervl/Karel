package se.skillytaire.belastingdienst.ee.service.account;

public interface PasswordCheck {
	boolean isValide(String username, String password);
}