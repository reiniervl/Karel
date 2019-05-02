package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;
import se.skillytaire.service.weather.api.PositiveInteger;

public class BootTestFactory implements ComparableTestObjectFactory<Boot> {
   @This
   private Verhuurder thisVerhuurder;
   @That
   private Verhuurder thatVerhuurder;
   @LessThen
   private Verhuurder anton;
   @GreaterThen
   private Verhuurder dirk;

   @This
   private PositiveInteger thisPositive;
   @That
   private PositiveInteger thatPositive;
   @LessThen
   private PositiveInteger joop;
   @GreaterThen
   private PositiveInteger henk;

   @Override
   public Boot createThat() {
      return new Boot(thatVerhuurder, thatPositive);
   }

   @Override
   public Boot createThis() {
      return new Boot(thisVerhuurder, thisPositive);
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Boot.class == type;
   }

   @Override
   public Boot createGreaterThen() {
      return new Boot(dirk, henk);
   }

   @Override
   public Boot createLessThen() {
      return new Boot(anton, joop);
   }

}
