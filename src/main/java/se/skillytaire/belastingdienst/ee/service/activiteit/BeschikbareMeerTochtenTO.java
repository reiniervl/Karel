package se.skillytaire.belastingdienst.ee.service.activiteit;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class BeschikbareMeerTochtenTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(min = 4, max = 24)
	private final String usernameVerhuurder;

	public BeschikbareMeerTochtenTO(final String usernameVerhuurder) {
		if (usernameVerhuurder == null) {
			throw new IllegalArgumentException("usernameVerhuurder is null");
		}
		this.usernameVerhuurder = usernameVerhuurder;
	}

	public String getUsernameVerhuurder() {
		return usernameVerhuurder;
	}

	@Override
	public String toString() {
		return "BeschikbareMeerTochtenTO [usernameVerhuurder=" + usernameVerhuurder + "]";
	}
}
