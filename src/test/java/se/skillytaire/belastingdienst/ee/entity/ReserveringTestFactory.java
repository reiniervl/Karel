package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class ReserveringTestFactory
      extends AbstractComparableTestObjectFactory<Reservering> {
   @Override
   public Reservering createThat() {
      Integer reserveringsNummer = new Integer(123456789);
      return new Reservering(reserveringsNummer);
   }

   @Override
   public Reservering createThis() {
      Integer reserveringsNummer = new Integer(987654321);
      return new Reservering(reserveringsNummer);
   }

   @Override
   public Reservering createGreaterThen() {
      Integer reserveringsNummer = new Integer(999999999);
      return new Reservering(reserveringsNummer);
   }

   @Override
   public Reservering createLessThen() {
      Integer reserveringsNummer = new Integer(111111111);
      return new Reservering(reserveringsNummer);
   }
}
