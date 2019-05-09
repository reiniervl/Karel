package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rvlstudio.annotation.Builder;
import com.rvlstudio.annotation.BuilderField;

//TODO functionele constructor voor username maken om nieuwe klant te initialiseren
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
@Builder
@Entity
@NamedQueries({
      @NamedQuery(name = Klant.FIND_BY_USERNAME, query = "SELECT k FROM Klant k WHERE k.username=:username") })
public class Klant extends AbstractEntity<Klant> {
   private static final long serialVersionUID = 1l;
   public static final String FIND_BY_USERNAME = "Klant_findByUsername";

	 @BuilderField
   @NotNull
   @Pattern(regexp = "[\\d\\w\\.]*@[\\d\\w\\.]*\\.[\\w]{2,3}$")
   private String email;

	 @BuilderField
   @NotNull
   @Size(min = 4, max = 24)
   @Column(unique = true, length = 24)
   private String username;

	 @BuilderField
   @NotNull
   private String password;

   /**
    * No-arg constructor conform JavaBeans
    */
   public Klant() {
   }

   public Klant(String username) {
      super();
      if (username == null) {
         throw new IllegalArgumentException("username mag niet null zijn");
      }
      this.username = username;
   }

   /**
    * Copy constructor
    *
    * @param klant
    *           Klant Object to be copied
    */
   public Klant(final Klant klant) {
      super(klant);
      this.username = klant.getUsername();
      this.setEmail(klant.getEmail());
      this.setPassword(klant.getPassword());
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

   public String getPassword() {
      return this.password;
   }

   public void setPassword(final String password) {
      this.password = password;
   }

   @Override
   public int compareTo(final Klant klant) {
      return this.getUsername().compareTo(klant.getUsername());
   }

   /**
    * De implementatie van de equals methode is niet compleet. De List van
    * {@code ActiviteitsReservering} in de property {@code reserveringen} wordt
    * niet vergeleken.
    */
   @Override
   public boolean equals(final Object o) {
      return super.equals(o) && this.getEmail().equals(((Klant) o).getEmail())
            && this.getPassword().equals(((Klant) o).getPassword());
   }

   /**
    * De property {@code username} is uniek. Hierdoor kan de hashcode
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