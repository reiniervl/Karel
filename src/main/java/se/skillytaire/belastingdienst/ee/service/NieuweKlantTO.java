package se.skillytaire.belastingdienst.ee.service;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NieuweKlantTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Pattern(regexp = "[\\w\\.]*@[\\w\\.]*\\.[\\w]{2,3}$")
	private final String email;

	@NotNull
	@Size(min = 4, max = 24)
	private final String username;

	@NotNull
	private final String password;
	
	public NieuweKlantTO(String username, String password, String email) {
		if (username == null) {
			 throw new IllegalArgumentException("username mag niet null zijn");
		}
		if (password == null) {
			 throw new IllegalArgumentException("password mag niet null zijn");
		}
		if (email == null) {
			 throw new IllegalArgumentException("email mag niet null zijn");
		}
		this.username = username;
		this.password = password;
		this.email = email;
 }

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "NieuweKlantTO [email=" + email + ", password=" + password + ", username=" + username + "]";
	}
}