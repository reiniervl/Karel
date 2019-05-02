package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;
import se.skillytaire.service.weather.api.PositiveInteger;

public class PositiveTestFactory
      implements ComparableTestObjectFactory<PositiveInteger> {
   @Override
   public PositiveInteger createThat() {
      return new PositiveInteger(2);
   }

   @Override
   public PositiveInteger createThis() {
      return new PositiveInteger(1);
   }

   @Override
   public boolean isTypeFor(final Class<?> type) {
      return PositiveInteger.class == type;
   }

   @Override
   public PositiveInteger createGreaterThen() {
      return new PositiveInteger(121);
   }

   @Override
   public PositiveInteger createLessThen() {
      return new PositiveInteger(0);
   }

}
