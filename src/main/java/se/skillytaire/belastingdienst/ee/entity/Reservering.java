package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {
      @UniqueConstraint(name = "UniqueReserverings", columnNames = {
            "ReserveringsNummer", "reserveringsDatum", "verloopDatum" }) })
public class Reservering extends AbstractEntity<Reservering> {
   private static final long serialVersionUID = 1L;
   @Basic
   private Integer reserveringsNummer;
   @NotNull
   @Basic
   private LocalDateTime reserveringsDatum;
   @Basic
   private LocalDateTime verloopDatum;

   /**
    * Developers should not use the default constructor. Please use the same
    * visibility modifier "protected" for overriding classes.
    */
   public Reservering() {
   }

   public Reservering(final Integer reserveringsNummer) {
      if (reserveringsNummer == null) {
         throw new IllegalArgumentException("Het reserveringsNummer is Null");
      }
      this.reserveringsNummer = reserveringsNummer;
   }

   public Reservering(final Reservering reservering) {
      super(reservering);
      this.reserveringsNummer = reservering.getReserveringsNummer();
      this.reserveringsDatum = reservering.getReserveringsDatum();
      this.verloopDatum = reservering.getVerloopDatum();
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Reservering [Reserveringsnummer= ");
      builder.append(this.reserveringsNummer);
      builder.append("Reserverings datum= ");
      builder.append(this.reserveringsDatum);
      builder.append("Verloop datum= ");
      builder.append(this.verloopDatum);
      builder.append("]");
      return builder.toString();
   }

   @Override
   public int compareTo(final Reservering that) {
      return this.getReserveringsNummer()
            .compareTo(that.getReserveringsNummer());
   }

   @Override
   public int hashCode() {
      return this.reserveringsNummer.hashCode();
   }

   public Integer getReserveringsNummer() {
      return this.reserveringsNummer;
   }

   // public void setReserveringsNummer(final Integer reserveringsNummer) {
   // this.reserveringsNummer = reserveringsNummer;
   // }

   public LocalDateTime getReserveringsDatum() {
      return this.reserveringsDatum;
   }

   public void setReserveringsDatum(final LocalDateTime reserveringsDatum) {
      this.reserveringsDatum = reserveringsDatum;
   }

   public LocalDateTime getVerloopDatum() {
      return this.verloopDatum;
   }

   public void setVerloopDatum(final LocalDateTime verloopDatum) {
      this.verloopDatum = verloopDatum;
   }
}
