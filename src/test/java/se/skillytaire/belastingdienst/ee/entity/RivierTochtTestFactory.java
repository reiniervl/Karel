package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class RivierTochtTestFactory
      extends AbstractComparableTestObjectFactory<RivierTocht> {
   @This
   private Periode thisPeriode;
   @That
   private Periode thatPeriode;
   @GreaterThen
   private Periode greaterThenPeriode;
   @LessThen
	 private Periode lessThenPeriode;
	 
   @Override
   public RivierTocht createThis() {
      return new RivierTocht(100D, this.thisPeriode);
   }

   @Override
   public RivierTocht createThat() {
      return new RivierTocht(10D, this.thatPeriode);
   }

   @Override
   public RivierTocht createGreaterThen() {
      return new RivierTocht(999D, this.greaterThenPeriode);
   }

   @Override
   public RivierTocht createLessThen() {
      return new RivierTocht(1D, this.lessThenPeriode);
   }
}
