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
   private Verhuurder lessThenVerhuurder;
   @GreaterThen
   private Verhuurder greaterThenVerhuurder;

   @This
   private PositiveInteger thisNummer;
   @That
   private PositiveInteger thatNummer;
   @LessThen
   private PositiveInteger lessThenNummer;
   @GreaterThen
   private PositiveInteger greaterThenNummer;

   @Override
   public Boot createThat() {
      return new Boot(this.thatVerhuurder, this.thatNummer);
   }

   @Override
   public Boot createThis() {
      return new Boot(this.thisVerhuurder, this.thisNummer);
   }

   @Override
   public Boot createGreaterThen() {
      return new Boot(this.greaterThenVerhuurder, this.greaterThenNummer);
   }

   @Override
   public Boot createLessThen() {
      return new Boot(this.lessThenVerhuurder, this.lessThenNummer);
   }

}
