package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class ReserveringTestFactory
      extends AbstractComparableTestObjectFactory<Reservering> {
	
@This
private Klant thisKlant;
@That
private Klant thatKlant;
@GreaterThen
private Klant greaterThenKlant;
@LessThen
private Klant lessThenKlant;
	
   @Override
   public Reservering createThat() {
      Integer reserveringsNummer = new Integer(123456789);
      return new Reservering(reserveringsNummer, thatKlant);
   }

   @Override
   public Reservering createThis() {
      Integer reserveringsNummer = new Integer(987654321);
      return new Reservering(reserveringsNummer, thisKlant);
   }

   @Override
   public Reservering createGreaterThen() {
      Integer reserveringsNummer = new Integer(999999999);
      return new Reservering(reserveringsNummer, greaterThenKlant);
   }

   @Override
   public Reservering createLessThen() {
      Integer reserveringsNummer = new Integer(111111111);
      return new Reservering(reserveringsNummer, lessThenKlant);
   }
}
