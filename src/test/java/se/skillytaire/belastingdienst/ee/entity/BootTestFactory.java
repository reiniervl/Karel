package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;
import se.skillytaire.java.datatype.PositiveInteger;

@TestFactory
public class BootTestFactory extends AbstractComparableTestObjectFactory<Boot> {
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
      return new Boot(this.thatVerhuurder, this.thatPositive);
   }

   @Override
   public Boot createThis() {
      return new Boot(this.thisVerhuurder, this.thisPositive);
   }

   @Override
   public Boot createGreaterThen() {
      return new Boot(this.dirk, this.henk);
   }

   @Override
   public Boot createLessThen() {
      return new Boot(this.anton, this.joop);
   }

}
