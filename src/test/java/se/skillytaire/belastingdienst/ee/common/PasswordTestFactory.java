package se.skillytaire.belastingdienst.ee.common;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class PasswordTestFactory extends AbstractComparableTestObjectFactory<Password> {

	@Override
	public Password createGreaterThen() {
		return new Password("zzzz");
	}

	@Override
	public Password createLessThen() {
		return new Password("aaaa");
	}

	@Override
	public Password createThat() {
		return new Password("cccc");
	}

	@Override
	public Password createThis() {
		return new Password("bbbb");
	}

}