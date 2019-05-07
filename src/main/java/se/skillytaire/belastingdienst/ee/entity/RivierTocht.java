package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("r")
public class RivierTocht extends Tocht<RivierTocht> {
   private static final long serialVersionUID = 1L;

   public RivierTocht() {
   }

   public RivierTocht(double prijs, Periode reserveringsPeriode) {
      super(prijs, reserveringsPeriode);
   }

   public RivierTocht(RivierTocht tocht) {
      super(tocht);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("RivierTocht []");
      return builder.toString();
   }

   @Override
   public int compareTo(final RivierTocht that) {
      return this.getReserveringsPeriode()
            .compareTo(that.getReserveringsPeriode());
   }

   @Override
   public int hashCode() {
      return this.getReserveringsPeriode().hashCode();
   }

}
