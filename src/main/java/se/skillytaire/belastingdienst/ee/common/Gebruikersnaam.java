package se.skillytaire.belastingdienst.ee.common;

import java.util.regex.Pattern;

public class Gebruikersnaam extends AbstractTransferableObject<String> {
   {
      this.addConstraint((v) -> v != null);
      this.addConstraint((v) -> Pattern.matches("^[a-zA-Z][\\w]{1,23}$", v));
   }

   public Gebruikersnaam(String value) {
      this.setValue(value);
   }
}
