package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class VerhuurderTestFactory implements ComparableTestObjectFactory<Verhuurder> {
	@This
	private Adres thisAdres;

	@Override
	public Verhuurder createThat() {
		return new Verhuurder("Joop", "TheOneAndOnly*Joop*", thisAdres);
	}

	@Override
	public Verhuurder createThis() {
		return new Verhuurder("Kim", "TheOneAndOnly*Kim*", thisAdres);
	}

	@Override
	public boolean isTypeFor(final Class<?> type) {
		return Verhuurder.class == type;
	}

	@Override
	public Verhuurder createGreaterThen() {
		return new Verhuurder("ZZZ", "TheOneAndOnly*ZZZ*", thisAdres);
	}

	@Override
	public Verhuurder createLessThen() {
		return new Verhuurder("AAA", "TheOneAndOnly*AAA*", thisAdres);
	}
}
