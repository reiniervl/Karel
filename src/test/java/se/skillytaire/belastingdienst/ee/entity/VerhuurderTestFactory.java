package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;

public class VerhuurderTestFactory
      implements ComparableTestObjectFactory<Verhuurder> {
   @Override
   public Verhuurder createThat() {
      return new Verhuurder("Joop");
   }

   @Override
   public Verhuurder createThis() {
      return new Verhuurder("Kim");
   }

   @Override
   public boolean isTypeFor(final Class<?> type) {
      return Verhuurder.class == type;
   }

   @Override
   public Verhuurder createGreaterThen() {
      return new Verhuurder("ZZZ");
   }

   @Override
   public Verhuurder createLessThen() {
      return new Verhuurder("AAA");
   }
}
