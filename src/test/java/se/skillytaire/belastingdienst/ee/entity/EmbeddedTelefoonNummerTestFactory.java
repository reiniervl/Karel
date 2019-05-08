package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.belastingdienst.ee.common.TelefoonNummer;
import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class EmbeddedTelefoonNummerTestFactory
      extends AbstractComparableTestObjectFactory<EmbeddedTelefoonNummer> {
   @This
   private TelefoonNummer thisTelefoonNummer;
   @That
   private TelefoonNummer thatTelefoonNummer;
   @LessThen
   private TelefoonNummer lessThenTelefoonNummer;
   @GreaterThen
   private TelefoonNummer greaterThanTelefoonNummer;

   @Override
   public EmbeddedTelefoonNummer createThat() {
      return new EmbeddedTelefoonNummer(this.thatTelefoonNummer);
   }

   @Override
   public EmbeddedTelefoonNummer createThis() {
      return new EmbeddedTelefoonNummer(this.thisTelefoonNummer);
   }

   @Override
   public EmbeddedTelefoonNummer createGreaterThen() {
      return new EmbeddedTelefoonNummer(this.greaterThanTelefoonNummer);
   }

   @Override
   public EmbeddedTelefoonNummer createLessThen() {
      return new EmbeddedTelefoonNummer(this.lessThenTelefoonNummer);
   }

}
