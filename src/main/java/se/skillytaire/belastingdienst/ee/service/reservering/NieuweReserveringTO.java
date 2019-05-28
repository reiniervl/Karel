package se.skillytaire.belastingdienst.ee.service.reservering;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NieuweReserveringTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(min = 4, max = 24)
	private final String usernameAccount;
	@NotNull
	@Size(min = 4, max = 24)
	private final String usernameVerhuurder;
	@NotNull
	private final Integer oidActiviteit;

	private final String soort;

	public NieuweReserveringTO(String usernameAccount, String usernameVerhuurder, Integer oidActiviteit, String soort) {
		if (usernameAccount == null) {
			 throw new IllegalArgumentException("usernameAccount mag niet null zijn");
		}
		if (usernameVerhuurder == null) {
			 throw new IllegalArgumentException("usernameVerhuuder mag niet null zijn");
		}
		if (oidActiviteit == null) {
			 throw new IllegalArgumentException("oid mag niet null zijn");
		}
		if (soort == null) {
			 throw new IllegalArgumentException("soort mag niet null zijn");
		}
		this.usernameAccount = usernameAccount;
		this.usernameVerhuurder = usernameVerhuurder;
		this.oidActiviteit = oidActiviteit;
		this.soort = soort;
 }

	public String getUsernameAccount() {
		return usernameAccount;
	}

	public String getUsernameVerhuuder() {
		return usernameVerhuurder;
	}

	public Integer getOidActiviteit() {
		return oidActiviteit;
	}

	public String getSoort() {
		return soort;
	}

	@Override
	public String toString() {
		return "NieuweReserveringTO [usernameAccount=" + usernameAccount + ", usernameVerhuuder=" + usernameVerhuurder
				+ ", oidActiviteit=" + oidActiviteit + ", soort=" + soort + "]";
	}
}
