package se.skillytaire.belastingdienst.ee.common;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class QRCodeTestFactory extends AbstractComparableTestObjectFactory<QRCode> {

	@Override
	public QRCode createGreaterThen() {
		return new QRCode(999);
	}

	@Override
	public QRCode createLessThen() {
		return new QRCode(0);
	}

	@Override
	public QRCode createThat() {
		return new QRCode(1);
	}

	@Override
	public QRCode createThis() {
		return new QRCode(2);
	}
}