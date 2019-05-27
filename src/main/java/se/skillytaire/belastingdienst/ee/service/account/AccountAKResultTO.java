package se.skillytaire.belastingdienst.ee.service.account;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.service.ResultTO;

public class AccountAKResultTO extends ResultTO<Account> {
	private static final long serialVersionUID = 1L;

	public AccountAKResultTO() {
		super(ResultTO.ACCOUNT_NIET_GEVONDEN);
	}

	public AccountAKResultTO(Account account) {
		super(account);
	}

	public AccountAKResultTO(int code) {
		super(code);
	}
}