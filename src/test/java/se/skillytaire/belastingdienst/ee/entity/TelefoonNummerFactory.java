package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;

public class TelefoonNummerFactory
      implements ComparableTestObjectFactory<TelefoonNummer> {
   @Override
   public TelefoonNummer createThat() {
      return new TelefoonNummer(31, 123456789);
   }

   @Override
   public TelefoonNummer createThis() {
      return new TelefoonNummer(31, 555555555);
   }

   @Override
   public TelefoonNummer createGreaterThen() {
      return new TelefoonNummer(44, 999999999);
   }

   @Override
   public TelefoonNummer createLessThen() {
      return new TelefoonNummer(30, 111111111);
   }

   @Override
   public boolean isTypeFor(final Class<?> type) {
      return TelefoonNummer.class == type;
   }
}
