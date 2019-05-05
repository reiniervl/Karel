package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class KlantTestFactory
      extends AbstractComparableTestObjectFactory<Klant> {
   @Override
   public Klant createThat() {
      Klant klant = new Klant();
      klant.setUsername("bert");
      klant.setPassword("password");
      klant.setEmail("test@createment.nl");
      return klant;
   }

   @Override
   public Klant createThis() {
      Klant klant = new Klant();
      klant.setUsername("ernie");
      klant.setPassword("password");
      klant.setEmail("test@createment.nl");
      return klant;
   }

   @Override
   public Klant createGreaterThen() {
      Klant klant = new Klant();
      klant.setUsername("zzz");
      klant.setPassword("password");
      klant.setEmail("test@createment.nl");
      return klant;
   }

   @Override
   public Klant createLessThen() {
      Klant klant = new Klant();
      klant.setUsername("aaa");
      klant.setPassword("password");
      klant.setEmail("test@createment.nl");
      return klant;
   }
}
