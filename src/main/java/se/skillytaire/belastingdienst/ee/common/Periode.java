package se.skillytaire.belastingdienst.ee.common;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Non-entity class voor {@code Activiteit} class
 *
 * @author Reinier van Leussen
 *
 */
public class Periode implements Comparable<Periode> {
   private ZoneId zoneId = ZoneId.systemDefault();
   private LocalDateTime start;
   private LocalDateTime eind;

   /**
    * No-arg constructor waardoor de ZoneId op de Steemstandaard wordt
    * ingesteld.
    */
   public Periode() {
   }

   /**
    *
    * @param zoneId
    *           Locale ZoneId, mag niet null zijn.
    */
   public Periode(final ZoneId zoneId) {
      if (zoneId == null) {
         throw new IllegalArgumentException("ZoneId mag niet null zijn");
      }
      this.setZoneId(zoneId);
   }

   public void start() {
      if (!this.isGestart()) {
         this.start = LocalDateTime.now(this.getZoneId());
      } else {
         throw new RuntimeException("Periode was al gestart");
      }
   }

   public void beeindig() {
      if (!this.isBeeindigd()) {
         this.eind = LocalDateTime.now(this.getZoneId());
      } else {
         throw new RuntimeException("Periode was al beeindigd");
      }
   }

   public boolean isGestart() {
      return this.getStart() != null;
   }

   public boolean isBeeindigd() {
      return this.getEind() != null;
   }

   public Duration getDuur() {
      if (this.isGestart() && this.isBeeindigd()) {
         return Duration.between(this.getStart(), this.getEind());
      } else {
         throw new RuntimeException(
               "Het is niet mogelijk om een duur te berekenen als een periode nog niet is gestart of beeindigd");
      }
   }

   @Override
   public int compareTo(final Periode periode) {
      int compare = 0;
      if (this.isGestart() && !periode.isGestart()) {
         compare = 1;
      } else if (!this.isGestart() && periode.isGestart()) {
         compare = -1;
      } else if (this.isBeeindigd() && periode.isBeeindigd()) {
         long verschil = this.getDuur().minus(periode.getDuur()).getSeconds();
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
      String start_sz =
            this.getStart() != null ? this.getStart().toString() : "-- : --";
      String eind_sz =
            this.getEind() != null ? this.getEind().toString() : "-- : --";
      return String.format("Start: %s / Eind: %s", start_sz, eind_sz);
   }

   // Getters & Setters
   public LocalDateTime getStart() {
      return this.start;
   }

   public void setStart(final LocalDateTime start) {
      this.start = start;
   }

   public LocalDateTime getEind() {
      return this.eind;
   }

   public void setEind(final LocalDateTime eind) {
      this.eind = eind;
   }

   public ZoneId getZoneId() {
      return this.zoneId;
   }

   public void setZoneId(final ZoneId zoneId) {
      this.zoneId = zoneId;
   }
}
