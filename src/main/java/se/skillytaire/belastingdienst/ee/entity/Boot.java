package se.skillytaire.belastingdienst.ee.entity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import se.skillytaire.java.datatype.PositiveInteger;

public class Boot extends AbstractEntity<Boot> {
   private static final long serialVersionUID = 1L;
   private int bootNummer;
   private Tocht deLaatsteTocht;
   @NotNull
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "vloot")
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

   public void start(final Tocht eenTocht) {
      // FIXME: design by contract
      this.deLaatsteTocht = eenTocht;
      this.tochtGeschiedenis.add(this.deLaatsteTocht);
      eenTocht.start();
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
         Periode actuelePeriode = t.getActuelePeriode();
         Optional<Duration> optionalDuration = actuelePeriode.getDuur();
         if (optionalDuration.isPresent()) {
            total = total.plus(optionalDuration.get());
         }
      }
      return total.getSeconds() > Boot.INSPECTIEDUUR.getSeconds();
   }

   private boolean isVrij() {
      return (!this.hasLaatsteTocht()) || (this.deLaatsteTocht.isBeeindigd());
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
