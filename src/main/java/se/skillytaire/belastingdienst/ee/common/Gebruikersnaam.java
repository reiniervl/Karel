package se.skillytaire.belastingdienst.ee.common;

public class Gebruikersnaam extends AbstractComparableObject<Gebruikersnaam>  {

   private static final long serialVersionUID = 1L;

   private final String value;

   public Gebruikersnaam(final String aValue) {
      if(aValue == null) {
         throw new IllegalArgumentException("aValue is void");
      }
      this.value = aValue;
   }

   public String getValue() {
      return value;
   }

   @Override
   public int compareTo(Gebruikersnaam that) {
      return getValue().compareTo(that.getValue());
   }

   @Override
   public int hashCode() {
      return getValue().hashCode();
   }

   @Override
   public String toString() {
      return getValue();
   }
}
