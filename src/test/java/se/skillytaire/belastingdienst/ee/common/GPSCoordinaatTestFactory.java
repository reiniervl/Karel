package se.skillytaire.belastingdienst.ee.common;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class GPSCoordinaatTestFactory
      extends AbstractComparableTestObjectFactory<GPSCoordinaat> {
   @Override
   public GPSCoordinaat createThat() {
      return new GPSCoordinaat(60.1383991, 14.3603374);
   }

   @Override
   public GPSCoordinaat createThis() {
      return GPSCoordinaat.STUGA;
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
