package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;

public class RivierTocht extends Tocht {
   private static final Duration CORRECTIEDUUR = Duration.ofMinutes(30);

   public RivierTocht() {
      super();
   }

   @Override
   public Duration geefDuur() {
      Duration duur = super.geefDuur();
      return duur.minus(RivierTocht.CORRECTIEDUUR);
   }

}
