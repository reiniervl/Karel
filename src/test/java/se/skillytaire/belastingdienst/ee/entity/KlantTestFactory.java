package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class KlantTestFactory
      extends AbstractComparableTestObjectFactory<Klant> {
   @Override
   public Klant createThat() {
      Klant klant = new Klant("bert", "password", "test@createment.nl");
      return klant;
   }

   @Override
   public Klant createThis() {
      Klant klant = new Klant("ernie", "password", "test@createment.nl");
      return klant;
   }

   @Override
   public Klant createGreaterThen() {
      Klant klant = new Klant("zzz", "password", "test@createment.nl");
      return klant;
   }

   @Override
   public Klant createLessThen() {
      Klant klant = new Klant("aaa", "password", "test@createment.nl");
      return klant;
   }
}
