package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.GreaterThen;
import se.skillytaire.course.tools.jlc.LessThen;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.course.tools.jlc.That;
import se.skillytaire.course.tools.jlc.This;

@TestFactory
public class AdresTestFactory
      extends AbstractComparableTestObjectFactory<Adres> {
   @This
   private EmbeddableGPSCoordinaat thisGPS;
   @That
   private EmbeddableGPSCoordinaat thatGPS;
   @LessThen
   private EmbeddableGPSCoordinaat lessThenGPS;
   @GreaterThen
   private EmbeddableGPSCoordinaat greaterThanGPS;

   @Override
   public Adres createThat() {
      return new Adres(this.thatGPS);
   }

   @Override
   public Adres createThis() {
      return new Adres(this.thisGPS);
   }

   @Override
   public Adres createGreaterThen() {
      return new Adres(this.greaterThanGPS);
   }

   @Override
   public Adres createLessThen() {
      return new Adres(this.lessThenGPS);
   }
}
