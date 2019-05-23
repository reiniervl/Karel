package se.skillytaire.belastingdienst.ee.service.account;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountAKTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(min = 4, max = 24)
	private final String usernameKlant;
	@NotNull
	@Size(min = 4, max = 24)
	private final String usernameVerhuurder;
	
	
	public AccountAKTO(String usernameKlant, String usernameVerhuurder) {
		if (usernameKlant == null) {
			 throw new IllegalArgumentException("usernameKlant mag niet null zijn");
		}
		if (usernameVerhuurder == null) {
			 throw new IllegalArgumentException("usernameVerhuurder mag niet null zijn");
		}
		this.usernameKlant = usernameKlant;
		this.usernameVerhuurder = usernameVerhuurder;
	}

	public String getUsernameKlant() {
		return usernameKlant;
	}

	public String getUsernameVerhuurder() {
		return usernameVerhuurder;
	}

	@Override
	public String toString() {
		return "AccountAKTO [usernameKlant=" + usernameKlant + ", usernameVerhuurder=" + usernameVerhuurder + "]";
	}
	
}
