package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("r")
@NamedQuery(name = RivierTocht.DELETE_BY_OID,
      query = "delete from RivierTocht a where a.oid=:oid")
public class RivierTocht extends Tocht<RivierTocht> {
   private static final long serialVersionUID = 1L;
   public static final String DELETE_BY_OID = "RivierTocht_DeleteByOid";
//   @NotNull
//   @Basic
//   private double prijsRivierTocht;

   public RivierTocht() {
   }

   public RivierTocht(double prijs, Periode reserveringsPeriode) {
      super(prijs, reserveringsPeriode);
//      this.prijsRivierTocht = prijs;
   }

   public RivierTocht(RivierTocht tocht) {
      super(tocht);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("RivierTocht []");
      builder.append(", reserveringsPeriode=");
      builder.append(super.getReserveringsPeriode());
      builder.append(", actuelePeriode=");
      builder.append(super.getActuelePeriode());
      builder.append(", prijs=");
//      builder.append(this.prijsRivierTocht);
//    builder.append(super.toString());
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

//   @Override
//   public void setPrijs(final double prijs) {
//      this.prijsRivierTocht = prijs;
////      super.setPrijs(prijs);
//   }
//
//   @Override
//   public double getPrijs() {
//      return this.prijsRivierTocht;
//   }
}
