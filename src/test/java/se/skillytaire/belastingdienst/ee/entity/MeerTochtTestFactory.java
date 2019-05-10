package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class MeerTochtTestFactory
      extends AbstractComparableTestObjectFactory<MeerTocht> {

				@This
				Boot thisBoot;
				@That
				Boot thatBoot;

				@GreaterThen
				Boot grooteBoot;

				@LessThen
				Boot subBoot;

   @GreaterThen
   private Periode greaterThenPeriode;

   @Override
   public MeerTocht createGreaterThen() {
      return new MeerTocht(grooteBoot, 1D, greaterThenPeriode);
   }

   @LessThen
   private Periode lessThenPeriode;

   @Override
   public MeerTocht createLessThen() {
      return new MeerTocht(subBoot, 1D, lessThenPeriode);
   }

   @That
   private Periode thatPeriode;

   @Override
   public MeerTocht createThat() {
      return new MeerTocht(thatBoot, 10D, thatPeriode);
   }

   @This
   private Periode thisPeriode;

   @Override
   public MeerTocht createThis() {
      return new MeerTocht(thisBoot, 100D, thisPeriode);
   }

}
