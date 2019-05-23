package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Embeddable;

import se.skillytaire.belastingdienst.ee.common.AbstractComparableObject;

/**
 * Non-entity class voor {@code Activiteit} class
 *
 * @author Reinier van Leussen
 *
 */
@Embeddable
public class Periode extends AbstractComparableObject<Periode>
      implements Cloneable {
   /**
    *
    */
   private static final long serialVersionUID = 1L;
   static final String PROPERTY_START = "start";
   static final String PROPERTY_EIND = "eind";
   static final String PROPERTY_DUUR = "duur";
   private Duration duur;
   private LocalDateTime start;
   private LocalDateTime eind;

   public Periode(final LocalDateTime start, final LocalDateTime eind) {
      if (start == null) {
         throw new IllegalArgumentException("start mag niet null zijn");
      }
      if (eind == null) {
         throw new IllegalArgumentException("eind mag niet null zijn");
      }
      if (eind.isBefore(start)) {
         throw new IllegalArgumentException(
               "eindtijd moet later zijn dan starttijd");
      }

      this.start = start;
      this.eind = eind;
      this.berekenDuur();
   }

   public Periode() {
   }

   public Periode(final Periode periode) {
      if (periode == null) {
         throw new IllegalArgumentException(
               "Periode mag niet gelijk zijn aan null");
      }
      this.start = periode.getStart();
      this.eind = periode.getEind();
      Optional<Duration> optionalDuration = periode.getDuur();
      if (optionalDuration.isPresent()) {
         this.duur = optionalDuration.get();
      }
   }

   @Override
   public Periode clone() {
      Periode clone;
      try {
         clone = (Periode) super.clone();
      } catch (CloneNotSupportedException e) {
         throw new AssertionError(e);
      }
      return clone;
   }

   public void start() {
      if (!this.isGestart()) {
         this.start = LocalDateTime.now();
      }
   }

   public void beeindig() {
      if (this.isGestart() && !this.isBeeindigd()) {
         this.eind = LocalDateTime.now();
         this.berekenDuur();
      }
   }

   public boolean isGestart() {
      return this.getStart() != null;
   }

   public boolean isBeeindigd() {
      return (this.getDuur().isPresent());

   }

   public Optional<Duration> getDuur() {
      return Optional.ofNullable(this.duur);
   }

   @Override
   public int compareTo(final Periode periode) {
      int compare = 0;
      if (this.isGestart() && !periode.isGestart()) {
         compare = 1;
      } else if (!this.isGestart() && periode.isGestart()) {
         compare = -1;
      } else if (this.isBeeindigd() && periode.isBeeindigd()) {
         long verschil = this.getDuur().get().minus(periode.getDuur().get())
               .getSeconds();
         if (verschil < 0L) {
            compare = -1;
         } else if (verschil > 0L) {
            compare = 1;
         }
      }
      return compare;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Periode [duur=");
      builder.append(this.duur);
      builder.append(", start=");
      builder.append(this.start);
      builder.append("]");
      return builder.toString();
   }

   private LocalDateTime getStart() {
      return this.start;
   }

   private LocalDateTime getEind() {
      return this.eind;
   }

   private void berekenDuur() {
      this.duur = Duration.between(this.getStart(), this.getEind());
   }

   @Override
   public int hashCode() {
      return AbstractComparableObject.HASH_PRIME;
   }
   
   public static final Periode oneDay () {
	   LocalDateTime nu = LocalDateTime.now();
	   return new Periode(nu, nu.plusDays(1));
   }
}
