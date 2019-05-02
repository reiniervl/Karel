package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Tocht {
   private LocalDateTime beginTijd;
   private LocalDateTime eindTijd;

   public Tocht() {

      this.zetBeginTijd(LocalDateTime.now());
   }

   public void beeindig() {
      this.zetEindTijd(LocalDateTime.now());
   }

   public Duration geefDuur() {
      return Duration.between(this.beginTijd, this.eindTijd);
   }

   public boolean isBeeindigd() {
      return (this.eindTijd != null);
   }

   private void zetBeginTijd(final LocalDateTime eenTijd) {
      this.beginTijd = eenTijd;
   }

   private void zetEindTijd(final LocalDateTime eenTijd) {
      this.eindTijd = eenTijd;
   }

   @Override
   public String toString() {
      return String.format("bt=%s + et=%s", this.beginTijd, this.eindTijd );
   }
}
