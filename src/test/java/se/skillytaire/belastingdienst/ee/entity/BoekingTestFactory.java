package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class BoekingTestFactory extends AbstractComparableTestObjectFactory<Boeking> {

	@This
	private Reservering thisReservering;
	@That
	private Reservering thatReservering;
	@LessThen
	private Reservering lessThenReservering;
	@GreaterThen
	private Reservering greaterThenReservering;
	
	@Override
	public Boeking createThat() {
		Boeking boeking = new Boeking(thatReservering);
		return boeking;
	}

	@Override
	public Boeking createThis() {
		Boeking boeking = new Boeking(thisReservering);
		return boeking;
	}

	@Override
	public Boeking createGreaterThen() {
		Boeking boeking = new Boeking(greaterThenReservering);
		return boeking;

	}

	@Override
	public Boeking createLessThen() {
		Boeking boeking = new Boeking(lessThenReservering);
		return boeking;
	}
}
