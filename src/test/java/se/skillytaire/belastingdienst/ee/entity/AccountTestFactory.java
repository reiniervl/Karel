package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class AccountTestFactory extends AbstractComparableTestObjectFactory<Account> {
	@This private Klant thisKlant;
	@That private Klant thatKlant;
	@GreaterThen private Klant uberKlant;
	@LessThen private Klant lesserKlant;

	@Override
	public Account createGreaterThen() {
		return new Account(uberKlant);
	}

	@Override
	public Account createLessThen() {
		return new Account(lesserKlant);
	}

	@Override
	public Account createThat() {
		return new Account(thatKlant);
	}

	@Override
	public Account createThis() {
		return new Account(thisKlant);
	}

}