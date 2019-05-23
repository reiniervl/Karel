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
	private final String usernameVerhuuder;
	@NotNull
	private final Integer oidActiviteit;

	public NieuweReserveringTO(String usernameAccount, String usernameVerhuuder, Integer oidActiviteit) {
		if (usernameAccount == null) {
			 throw new IllegalArgumentException("usernameAccount mag niet null zijn");
		}
		if (usernameVerhuuder == null) {
			 throw new IllegalArgumentException("usernameVerhuuder mag niet null zijn");
		}
		if (oidActiviteit == null) {
			 throw new IllegalArgumentException("oid mag niet null zijn");
		}
		this.usernameAccount = usernameAccount;
		this.usernameVerhuuder = usernameVerhuuder;
		this.oidActiviteit = oidActiviteit;
 }

	public String getUsernameAccount() {
		return usernameAccount;
	}

	public String getUsernameVerhuuder() {
		return usernameVerhuuder;
	}

	public Integer getOidActiviteit() {
		return oidActiviteit;
	}

	@Override
	public String toString() {
		return "NieuweReserveringTO [usernameAccount=" + usernameAccount + ", usernameVerhuuder=" + usernameVerhuuder
				+ ", oidActiviteit=" + oidActiviteit + "]";
	}
}
