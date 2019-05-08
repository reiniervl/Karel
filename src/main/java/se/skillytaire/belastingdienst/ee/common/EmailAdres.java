package se.skillytaire.belastingdienst.ee.common;

public final class EmailAdres extends AbstractComparableObject<EmailAdres> {

   private static final long serialVersionUID = 1L;
   // @javax.validation.constraints.Pattern(regexp="???")
   private final String value;
   // {
   // this.addConstraint((v) -> v != null);
   // this.addConstraint((v) -> Pattern.matches(
   // "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
   // v));
   // }

   public EmailAdres(final String aValue) {
      if(aValue == null) {
         throw new IllegalArgumentException("aValue is void");
      }
      this.value = aValue;
   }

   public String getValue() {
      return value;
   }

   @Override
   public int compareTo(EmailAdres that) {
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
