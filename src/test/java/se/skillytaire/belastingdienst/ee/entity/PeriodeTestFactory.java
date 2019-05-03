package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;

public class PeriodeTestFactory
      implements ComparableTestObjectFactory<Periode> {
   private static final LocalDateTime NU = LocalDateTime.now();

   @Override
   public Periode createThat() {
      Periode periode = new Periode(PeriodeTestFactory.NU,
            PeriodeTestFactory.NU.plusHours(1));
      return periode;
   }

   @Override
   public Periode createThis() {
      Periode periode = new Periode(PeriodeTestFactory.NU,
            PeriodeTestFactory.NU.plusHours(2));
      return periode;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      // TODO Auto-generated method stub
      return Periode.class == type;
   }

   @Override
   public Periode createGreaterThen() {
      Periode periode = new Periode(PeriodeTestFactory.NU,
            PeriodeTestFactory.NU.plusYears(1));
      return periode;
   }

   @Override
   public Periode createLessThen() {
      Periode periode =
            new Periode(PeriodeTestFactory.NU, PeriodeTestFactory.NU);
      return periode;
   }

}
