package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import se.skillytaire.belastingdienst.ee.common.AbstractComparableObject;
import se.skillytaire.didactic.annotation.fluent.FluentConstructorArgument;

/**
 * Een telefoonnummer is een internationaal nummer met nationaal nummer
 * gecombineerd.
 *
 */

@Embeddable
public class TelefoonNummer extends AbstractComparableObject<TelefoonNummer> {
   private static final long serialVersionUID = 1L;
   @Basic
   @NotNull
   private Integer landNummer;
   @Basic
   @NotNull
   private Integer nationaalNummer;

   public TelefoonNummer() {
   }

   @FluentConstructorArgument(fieldName = "landNummer")
   @FluentConstructorArgument(fieldName = "nationaalNummer")
   public TelefoonNummer(final int landNummer, final int nationaalNummer) {
      super();
      this.landNummer = landNummer;
      this.nationaalNummer = nationaalNummer;
   }

   @Override
   public int hashCode() {
      return AbstractComparableObject.HASH_PRIME ^ this.getLandNummer()
            ^ this.getNationaalNummer();
   }

   @Override
   public int compareTo(final TelefoonNummer that) {
      int compareTo;
      if (this.getLandNummer().equals(that.getLandNummer())) {
         compareTo = this.getNationaalNummer()
               .compareTo(that.getNationaalNummer());
      } else {
         compareTo = this.getLandNummer().compareTo(that.getLandNummer());
      }
      return compareTo;
   }

   @Override
   public String toString() {
      return String.format("+%d %d", this.landNummer, this.nationaalNummer);
   }

   public Integer getLandNummer() {
      return this.landNummer;
   }

   public Integer getNationaalNummer() {
      return this.nationaalNummer;
   }
}
