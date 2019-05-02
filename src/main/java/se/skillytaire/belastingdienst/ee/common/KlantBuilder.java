package se.skillytaire.belastingdienst.ee.common;

import se.skillytaire.belastingdienst.ee.annotation.Builder;
import se.skillytaire.belastingdienst.ee.entity.Klant;

@Builder
public interface KlantBuilder {
//   interface KlantUsername {
//      KlantPassword username(String username);
//   }
//   interface KlantPassword {
//      KlantOptional password(String password);
//   }
//
//   interface KlantOptional {
//      KlantOptional email(String email);
//      Klant build();
//   }
   KlantBuilder addUsername(String username);
   KlantBuilder addPassword(String password);
   KlantBuilder addEmail(String email);
   Klant build();
}
