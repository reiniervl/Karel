package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import se.skillytaire.belastingdienst.ee.common.KlantBuilder;

/**
 * <p>
 * De {@code Klant} class is bedoeld als entity class voor de BootjesCase.
 * Gebruik van deze class buiten de JPA wordt afgeraden, omdat deze class alleen
 * een no-arg constructor heeft om te voldoen aan het JavaBean contract.
 * </p>
 * <p>
 * Zie de class {@code se.skillytaire.belastingdienst.ee.entity.KlantTest} voor
 * de JUnit test cases.
 * </p>
 *
 * @author Reinier van Leussen
 * @version 0.3.2
 *
 */
@Entity
@Table(name = "klanten")
public class Klant extends AbstractEntity<Klant, Integer> {
   private static final long serialVersionUID = 1l;

   public static KlantBuilder getBuilder() {
      return new KlantBuilder() {
         private Klant klant = new Klant();

         @Override
         public KlantBuilder addEmail(final String email) {
            this.klant.setEmail(email);
            return this;
         }

         @Override
         public KlantBuilder addUsername(final String username) {
            this.klant.setUsername(username);
            return this;
         }

         @Override
         public KlantBuilder addPassword(final String password) {
            // TODO Auto-generated method stub
            return this;
         }

         @Override
         public Klant build() {
            return this.klant;
         }

      };

   }
   // private static String emailPattern =
   // "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

   // FIXME: pattern is not comprehensive enough
   @NotNull
   @Pattern(regexp = "[\\d\\w\\.]*@[\\d\\w\\.]*\\.[\\w]{2,3}$")
   private String email;

   @NotNull
   @Size(min = 4, max = 24)
   @Column(unique = true, length = 24)
   private String username;

   @NotNull
   private String password;

   /**
    * No-arg constructor conform JavaBeans
    */
   public Klant() {
   }

   /**
    * Copy constructor
    *
    * @param klant
    *           Klant Object to be copied
    */
   public Klant(final Klant klant) {
      super(klant);
      this.setEmail(new String(klant.getEmail()));
      this.setUsername(new String(klant.getUsername()));
      this.setPassword(new String(klant.getPassword()));
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(final String email) {
      this.email = email;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(final String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(final String password) {
      this.password = password;
   }

   @Override
   public int compareTo(final Klant klant) {
      int compareTo = 0;
      // TODO: implementation
      compareTo = this.getUsername().compareTo(klant.getUsername()); // Placeholder
                                                                     // vergelijking
      return compareTo;
   }

   /**
    * De implementatie van de equals methode is niet compleet. De List van
    * {@code ActiviteitsReservering} in de property {@code reserveringen} wordt
    * niet vergeleken.
    */
   @Override
   public boolean equals(final Object o) {
      boolean equals = false;
      if (o instanceof Klant) {
         Klant that = (Klant) o;
         if (this.getEmail().equals(that.getEmail())
               && this.getUsername().equals(that.getUsername())
               && this.getPassword().equals(that.getPassword())) {
            equals = true;
         }
      }
      return equals;
   }

   /**
    * De property {@code username} is uniek. Hierdoor kan de hascode
    * doorverwezen worden.
    */
   @Override
   public int hashCode() {
      return this.getUsername().hashCode();
   }

   @Override
   public String toString() {
      StringBuilder sb =
            new StringBuilder().append("username: ").append(this.getUsername())
                  .append("\nemail: ").append(this.getEmail());
      return sb.toString();
   }
}
