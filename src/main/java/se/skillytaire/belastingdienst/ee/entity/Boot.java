package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import se.skillytaire.service.weather.api.PositiveInteger;

public class Boot extends AbstractEntity<Boot, Integer> {
   private static final long serialVersionUID = 1L;
   private int bootNummer;
   private Tocht deLaatsteTocht;
   @NotNull
   private Verhuurder eigenaar;
   private ArrayList<Tocht> tochtGeschiedenis;
   private static final Duration INSPECTIEDUUR = Duration.ofSeconds(10);

   public Boot() {
   }

   public Boot(final Verhuurder eenEigenaar, final PositiveInteger nummer) {
      if (eenEigenaar == null) {
         throw new IllegalArgumentException("eenEigenaar mag niet 'null' zijn");
      }
      if (nummer == null) {
         throw new IllegalArgumentException("Nummertje!");
      }
      this.eigenaar = eenEigenaar;
      this.bootNummer = nummer.intValue();
      this.tochtGeschiedenis = new ArrayList<Tocht>();
   }

   @SuppressWarnings("unchecked")
   public Boot(final Boot that) {
      super(that);
      this.bootNummer = that.geefNummer();
      this.eigenaar = that.getEigenaar();
      this.tochtGeschiedenis =
            (ArrayList<Tocht>) that.tochtGeschiedenis.clone();
      if (that.hasLaatsteTocht()) {
         // FIX-ME: Clone()
         this.deLaatsteTocht = that.deLaatsteTocht;
      }
   }

   public Duration beeindigTocht() {
      this.deLaatsteTocht.beeindig();
      return this.deLaatsteTocht.geefDuur();
   }

   public Tocht gaMeerTochtMaken() {
      return this.gaTochtMaken(new MeerTocht());
   }

   public Tocht gaRivierTochtMaken() {
      return this.gaTochtMaken(new RivierTocht());
   }

   private Tocht gaTochtMaken(final Tocht eenTocht) {
      this.deLaatsteTocht = eenTocht;
      this.tochtGeschiedenis.add(this.deLaatsteTocht);
      return this.deLaatsteTocht;
   }

   public int geefNummer() {
      return this.bootNummer;
   }

   public boolean heeftNummer(final int nummer) {
      return (this.bootNummer == nummer);
   }

   public boolean isBeschikbaar() {
      return ((this.isVrij()) && (!this.isInspectieNodig()));
   }

   public boolean isInspectieNodig() {
      Duration total = Duration.ZERO;
      for (Tocht t : this.tochtGeschiedenis) {
         total = total.plus(t.geefDuur());
      }
      return total.getSeconds() > Boot.INSPECTIEDUUR.getSeconds();
   }

   private boolean isVrij() {
      return ((!this.hasLaatsteTocht()) || (this.deLaatsteTocht.isBeeindigd()));
   }

   public boolean hasLaatsteTocht() {
      return this.deLaatsteTocht != null;
   }

   public void uitvoerenInspectie() {
      this.tochtGeschiedenis.clear();
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Boot [bootNummer=");
      builder.append(this.bootNummer);
      builder.append(", deLaatsteTocht=");
      builder.append(this.deLaatsteTocht);
      builder.append(", eenBootTochtenbak=");
      builder.append(this.tochtGeschiedenis);
      builder.append("]");
      return builder.toString();
   }

   public Verhuurder getEigenaar() {
      return this.eigenaar;
   }

   @Override
   public int compareTo(final Boot that) {
      int compareTo = this.geefNummer() - that.geefNummer();
      if (compareTo == 0) {
         compareTo = this.getEigenaar().compareTo(that.getEigenaar());
      }
      return compareTo;
   }

   @Override
   public int hashCode() {
      return this.geefNummer();
   }

}
