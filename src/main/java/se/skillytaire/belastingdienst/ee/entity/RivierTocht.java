package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;

public class RivierTocht extends Tocht {
   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private static final Duration CORRECTIEDUUR = Duration.ofMinutes(30);

   public RivierTocht() {
      super();
   }

   @Override
   public int compareTo(final Tocht o) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int hashCode() {
      // TODO Auto-generated method stub
      return 0;
   }

   // @Override
   // public Duration geefDuur() {
   // Duration duur = super.geefDuur();
   // return duur.minus(RivierTocht.CORRECTIEDUUR);
   // }

}
