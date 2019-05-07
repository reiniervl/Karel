package se.skillytaire.belastingdienst.ee.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

//TODO mapping van vloot en adres en telefoonnummer toevoegen.
@Entity
public class Verhuurder extends AbstractEntity<Verhuurder> {
   private static final long serialVersionUID = 1L;
   @NotNull
   /// @Column(unique=true)
   private String userName;
   @Null
   private String name;
   @Null
   private Adres adres;
   @Null
   private TelefoonNummer telefoonNummer;
   // Locaties?????

   private ArrayList<Boot> vloot = new ArrayList<>();

   public boolean add(final Boot e) {
      return this.vloot.add(e);
   }

   public boolean remove(final Boot b) {
      return this.vloot.remove(b);
   }

   private ArrayList<Tocht> tochtenbak = new ArrayList<>();

   public boolean add(final Tocht e) {
      return this.tochtenbak.add(e);
   }

   public boolean remove(final Tocht t) {
      return this.tochtenbak.remove(t);
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
      this.vloot = (ArrayList<Boot>) that.vloot.clone();
      this.tochtenbak = (ArrayList<Tocht>) that.tochtenbak.clone();
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

   public TelefoonNummer getTelefoonNummer() {
      return this.telefoonNummer;
   }

   public void setTelefoonNummer(final TelefoonNummer telefoonNummer) {
      this.telefoonNummer = telefoonNummer;
   }

   public String getUserName() {
      return this.userName;
   }
}
