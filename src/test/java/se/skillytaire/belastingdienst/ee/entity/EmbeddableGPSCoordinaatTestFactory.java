package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.course.tools.jlc.ComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

public class EmbeddableGPSCoordinaatTestFactory
      implements ComparableTestObjectFactory<EmbeddableGPSCoordinaat> {
   @This
   private GPSCoordinaat thisGPS;
   @That
   private GPSCoordinaat thatGPS;
   @LessThen
   private GPSCoordinaat lessThenGPS;
   @GreaterThen
   private GPSCoordinaat greaterThanGPS;

   @Override
   public EmbeddableGPSCoordinaat createThat() {
      return new EmbeddableGPSCoordinaat(this.thatGPS);
   }

   @Override
   public EmbeddableGPSCoordinaat createThis() {
      return new EmbeddableGPSCoordinaat(this.thisGPS);
   }

   @Override
   public boolean isTypeFor(final Class<?> arg0) {
      return EmbeddableGPSCoordinaat.class == arg0;
   }

   @Override
   public EmbeddableGPSCoordinaat createGreaterThen() {
      return new EmbeddableGPSCoordinaat(this.greaterThanGPS);
   }

   @Override
   public EmbeddableGPSCoordinaat createLessThen() {
      return new EmbeddableGPSCoordinaat(this.lessThenGPS);
   }

}
