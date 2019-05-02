package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;

public class AdresTestFactory implements ComparableTestObjectFactory<Adres> {
   @Override
   public Adres createThat() {
      GPSCoordinaat gps = new GPSCoordinaat(60.1383991, 14.3603374);
      return new Adres(gps);
   }

   @Override
   public Adres createThis() {
      GPSCoordinaat gps = new GPSCoordinaat(61.1383991, 14.3603374);
      return new Adres(gps);
   }

   @Override
   public boolean isTypeFor(final Class<?> type) {
      return Adres.class == type;
   }

   @Override
   public Adres createGreaterThen() {
      GPSCoordinaat gps = new GPSCoordinaat(70.1383991, 24.3603374);
      return new Adres(gps);
   }

   @Override
   public Adres createLessThen() {
      GPSCoordinaat gps = new GPSCoordinaat(50.1383991, 04.3603374);
      return new Adres(gps);
   }
}
