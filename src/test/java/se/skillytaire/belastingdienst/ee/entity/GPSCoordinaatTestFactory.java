package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;

public class GPSCoordinaatTestFactory
      implements ComparableTestObjectFactory<GPSCoordinaat> {
   @Override
   public GPSCoordinaat createThat() {
      return new GPSCoordinaat(60.1383991, 14.3603374);
   }

   @Override
   public GPSCoordinaat createThis() {
      return new GPSCoordinaat();
   }

   @Override
   public boolean isTypeFor(final Class<?> type) {
      return GPSCoordinaat.class == type;
   }

   @Override
   public GPSCoordinaat createGreaterThen() {
      return GPSCoordinaat.MAX_VALUE;
   }

   @Override
   public GPSCoordinaat createLessThen() {
      return GPSCoordinaat.MIN_VALUE;
   }
}
