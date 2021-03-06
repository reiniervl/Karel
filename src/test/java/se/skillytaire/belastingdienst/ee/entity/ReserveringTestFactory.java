package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;
import java.time.Month;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class ReserveringTestFactory extends AbstractComparableTestObjectFactory<Reservering> {

	@This
	private Account thisaccount;
	@That
	private Account thataccount;
	@GreaterThen
	private Account greaterThenaccount;
	@LessThen
	private Account lessThenaccount;
	@This
	private RivierTocht thisRivierTocht;
	// @That
	// private RivierTocht thatRivierTocht;
	// @GreaterThen
	// private RivierTocht greaterRivierTocht;
	// @LessThen
	// private RivierTocht lessRivierTocht;
	final LocalDateTime TIJD = LocalDateTime.of(2019, Month.MAY, 1, 12, 10);

	@Override
	public Reservering createThat() {
		Reservering reservering = new Reservering(thataccount, thisRivierTocht);
		reservering.setReserveringsDatum(TIJD);
		return reservering;
	}

	@Override
	public Reservering createThis() {
		Reservering reservering = new Reservering(thisaccount, thisRivierTocht);
		reservering.setReserveringsDatum(TIJD);
		return reservering;
	}

	@Override
	public Reservering createGreaterThen() {
		Reservering reservering = new Reservering(greaterThenaccount, thisRivierTocht);
		reservering.setReserveringsDatum(TIJD);
		return reservering;

	}

	@Override
	public Reservering createLessThen() {
		Reservering reservering = new Reservering(lessThenaccount, thisRivierTocht);
		reservering.setReserveringsDatum(TIJD);
		return reservering;
	}
}
