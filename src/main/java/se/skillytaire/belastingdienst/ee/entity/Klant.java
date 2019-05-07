package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@NamedQueries({
	@NamedQuery(name=Klant.FIND_BY_USERNAME, query="SELECT k FROM klanten WHERE k.username=:username")
})
public class Klant extends AbstractEntity<Klant> {
	 private static final long serialVersionUID = 1l;
	 public static final String FIND_BY_USERNAME = "Klant_findByUsername";

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
			compareTo = this.getUsername().compareTo(klant.getUsername());
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
      StringBuilder sb = new StringBuilder().append("username: ")
            .append(this.getUsername()).append("\nemail: ")
            .append(this.getEmail());
      return sb.toString();
   }
}
