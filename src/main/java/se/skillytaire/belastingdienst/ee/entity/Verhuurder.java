package se.skillytaire.belastingdienst.ee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.rvlstudio.annotation.Builder;
import com.rvlstudio.annotation.BuilderField;

@Builder
@Entity
@NamedQueries({
	@NamedQuery(name = Verhuurder.FIND_VERHUURDER_BY_USERNAME, query = "SELECT v FROM Verhuurder v WHERE v.userName=:username")
})
public class Verhuurder extends AbstractEntity<Verhuurder> {
	 private static final long serialVersionUID = 1L;
	 public static final String FIND_VERHUURDER_BY_USERNAME = "FIND_VERHUURDER_BY_USERNAME";
	 @BuilderField
   @NotNull
   @Column(unique=true)
	 private String userName;
	 @BuilderField
   @Null
	 private String name;
	 @BuilderField
	 @Null
	 @OneToOne(cascade = CascadeType.ALL)
   private Adres adres;
   @Null
   @Embedded
   private EmbeddedTelefoonNummer telefoonNummer;
   // Locaties?????

   @OneToMany(cascade = CascadeType.ALL)
   private List<Boot> vloot = new ArrayList<>();

   public boolean add(final Boot e) {
      return this.vloot.add(e);
   }

   public boolean remove(final Boot b) {
      return this.vloot.remove(b);
   }

   /** do not use */
   public Verhuurder() {
      super();
   }

   @SuppressWarnings("unchecked")
   public Verhuurder(final Verhuurder that) {
      super(that);
      this.userName = that.getUserName();
      this.adres = that.getAdres();
      this.name = that.getName();
      this.telefoonNummer = that.getTelefoonNummer();
      this.vloot = (ArrayList<Boot>) ((ArrayList<Boot>)that.vloot).clone();
   }

   public Verhuurder(final String userName) {
      if (userName == null) {
         throw new IllegalArgumentException("userName is void");
      }
      this.userName = userName;
   }

   @Override
   public int compareTo(final Verhuurder that) {
      return this.getUserName().compareTo(that.getUserName());
   }

   @Override
   public int hashCode() {
      return this.getUserName().hashCode();
   }

   public String getName() {
      return this.name;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public Adres getAdres() {
      return this.adres;
   }

   public boolean hasAdres() {
      return this.adres != null;
   }

   public void setAdres(final Adres adres) {
      this.adres = adres;
   }

   public EmbeddedTelefoonNummer getTelefoonNummer() {
      return this.telefoonNummer;
   }

   public void setTelefoonNummer(final EmbeddedTelefoonNummer telefoonNummer) {
      this.telefoonNummer = telefoonNummer;
   }

   public String getUserName() {
      return this.userName;
   }
}