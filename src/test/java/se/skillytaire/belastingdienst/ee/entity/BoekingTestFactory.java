package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;
import java.time.Month;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class BoekingTestFactory extends AbstractComparableTestObjectFactory<Boeking> {

	// @This
	// private Reservering thisReservering;
	// @That
	// private Reservering thatReservering;
	// @LessThen
	// private Reservering lessThenReservering;
	// @GreaterThen
	// private Reservering greaterThenReservering;

	@This Adres adres;
	static final LocalDateTime TIJD = LocalDateTime.of(2019, Month.MAY, 1, 12, 10);

	static final LocalDateTime TIJD2 = LocalDateTime.of(2019, Month.MAY, 1, 12, 10).plusHours(1);
	static final Periode period = new Periode(TIJD, TIJD2);
	
	@Override
	public Boeking createThat() {

		Boeking boeking = new Boeking(
			new Reservering(new Account(
				new Klant("bert", "password", "mail@email.com"),
				new Verhuurder("Joop", "TheOneAndOnly*Joop*", adres)
			), new MeerTocht(12.0, period))
		);
		boeking.getReservering().setReserveringsDatum(TIJD);
		return boeking;
	}

	@Override
	public Boeking createThis() {
		Boeking boeking = new Boeking(
			new Reservering(new Account(
				new Klant("ernie", "password", "mail@email.com"),
				new Verhuurder("Joop", "TheOneAndOnly*Joop*", adres)
			), new MeerTocht(12.0, period))
		);
		boeking.getReservering().setReserveringsDatum(TIJD);
		return boeking;
	}

	@Override
	public Boeking createGreaterThen() {
		Boeking boeking = new Boeking(
			new Reservering(new Account(
				new Klant("zzz", "password", "mail@email.com"),
				new Verhuurder("Joop", "TheOneAndOnly*Joop*", adres)
			), new MeerTocht(12.0, period))
		);
		boeking.getReservering().setReserveringsDatum(TIJD);
		return boeking;

	}

	@Override
	public Boeking createLessThen() {
		Boeking boeking = new Boeking(
			new Reservering(new Account(
				new Klant("aaa", "password", "mail@email.com"),
				new Verhuurder("Joop", "TheOneAndOnly*Joop*", adres)
			), new MeerTocht(12.0, period))
		);
		boeking.getReservering().setReserveringsDatum(TIJD);
		return boeking;
	}
}
