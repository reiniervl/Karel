package se.skillytaire.belastingdienst.ee.common;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class GebruikersnaamTestFactory  
extends AbstractComparableTestObjectFactory<Gebruikersnaam> {

   @Override
   public Gebruikersnaam createGreaterThen() {
      return new Gebruikersnaam("z");
   }

   @Override
   public Gebruikersnaam createLessThen() {
      return new Gebruikersnaam("a");
   }

   @Override
   public Gebruikersnaam createThat() {
      return new Gebruikersnaam("createThatZ");
   }

   @Override
   public Gebruikersnaam createThis() {
      return new Gebruikersnaam("createThatA");
   }

}
