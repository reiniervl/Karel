package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
@DiscriminatorColumn(name = "type")
public abstract class Tocht<T extends Tocht<T>> extends AbstractEntity<T> {
   private static final long serialVersionUID = 1L;
   @NotNull
   @Embedded
   @AttributeOverrides({
         @AttributeOverride(name = Periode.PROPERTY_START, column = @Column(name = "reserveringstart")),
         @AttributeOverride(name = Periode.PROPERTY_EIND, column = @Column(name = "reserveringeind")),
         @AttributeOverride(name = Periode.PROPERTY_DUUR, column = @Column(name = "reserveringduur")) })
   private Periode reserveringsPeriode;
   @NotNull
   @Embedded
   @AttributeOverrides({
         @AttributeOverride(name = Periode.PROPERTY_START, column = @Column(name = "actuelestart")),
         @AttributeOverride(name = Periode.PROPERTY_EIND, column = @Column(name = "actueleeind")),
         @AttributeOverride(name = Periode.PROPERTY_DUUR, column = @Column(name = "actueleduur")) })
   private Periode actuelePeriode;
   @NotNull
   private Double prijs;

   /**
    * Developers should not use the default constructor. Please use the same
    * visibility modifier "protected" for overriding classes.
    */
   public Tocht() {
   }

   public Tocht(final Double prijs, final Periode reserveringsPeriode) {
      if (prijs == null) {
         throw new IllegalArgumentException("De prijs is null");
      }
      if (reserveringsPeriode == null) {
         throw new IllegalArgumentException("De reserveringsPeriode is null");
      }
      if (!reserveringsPeriode.isBeeindigd()) {
         throw new IllegalArgumentException(
               "De reserveringsPeriode is niet valide");
      }
      this.prijs = prijs;
      this.reserveringsPeriode = reserveringsPeriode.clone();
      this.actuelePeriode = new Periode();
   }

   public Tocht(final T tocht) {
      super(tocht);
      this.reserveringsPeriode = tocht.getReserveringsPeriode();
      this.actuelePeriode = tocht.getActuelePeriode();
      this.prijs = tocht.getPrijs();
   }

   @Override
   public int compareTo(final T that) {
      Tocht<T> deTocht = that;
      return this.reserveringsPeriode.compareTo(deTocht.reserveringsPeriode);
   }

   @Override
   public int hashCode() {
      return this.reserveringsPeriode.hashCode();
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(", reserveringsPeriode=");
      builder.append(this.reserveringsPeriode);
      builder.append(", actuelePeriode=");
      builder.append(this.actuelePeriode);
      builder.append(", prijs=");
      builder.append(this.prijs);
      return builder.toString();
   }

   public Periode getReserveringsPeriode() {
      return this.reserveringsPeriode != null ? this.reserveringsPeriode.clone()
            : null;

   }

   public Periode getActuelePeriode() {
      return this.actuelePeriode != null ? this.actuelePeriode.clone()
            : null;
   }

   public Double getPrijs() {
      return this.prijs;
   }

   public void setPrijs(final Double prijs) {
      this.prijs = prijs;
   }

   public boolean isBeeindigd() {
      return this.actuelePeriode.isBeeindigd();
   }

   public void start() {
      this.actuelePeriode.start();
   }
}
