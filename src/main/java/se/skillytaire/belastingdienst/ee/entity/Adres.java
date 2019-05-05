package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import se.skillytaire.didactic.annotation.fluent.Fluent;
import se.skillytaire.didactic.annotation.fluent.FluentConstructorArgument;

@Fluent
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueAdresLocation",
      columnNames = { "longtitude", "latitude" }) })
@NamedQueries({ @NamedQuery(name = Adres.FIND_BY_GPS,
      query = "select a from Adres a where a.gpsCoordinaat.latitude=:latitude and a.gpsCoordinaat.longtitude=:longtitude"),
      @NamedQuery(name = Adres.DELETE_BY_OID,
            query = "delete from Adres a where a.oid=:oid") })
public class Adres extends AbstractEntity<Adres> {
   public static final String FIND_BY_GPS = "Adres_DeleteByGPS";
   public static final String DELETE_BY_OID = "Adres_DeleteByOid";
   private static final long serialVersionUID = 1L;
   @Basic
   private String street;
   @Basic
   private Integer houseNumber;
   @Basic
   private String numberSuffix;
   @Basic
   private String city;
   @Basic
   private String state;
   @Basic
   private String postalCode;

   @Basic
   private String country;

   @Embedded
   @NotNull
   @AttributeOverrides({
         @AttributeOverride(name = EmbeddableGPSCoordinaat.PROPERTY_LONG,
               column = @Column(name = "longtitude")),
         @AttributeOverride(name = EmbeddableGPSCoordinaat.PROPERTY_LAT,
               column = @Column(name = "latitude")) })
   private EmbeddableGPSCoordinaat gpsCoordinaat;

   /**
    * Developers should not use the default constructor. Please use the same
    * visibility modifier "protected" for overriding classes.
    */
   public Adres() {
   }

   @FluentConstructorArgument(fieldName = "gpsCoordinaat")
   public Adres(final EmbeddableGPSCoordinaat gpsCoordinaat) {
      if (gpsCoordinaat == null) {
         throw new IllegalArgumentException("Het GPS Coordinaat is null");
      }
      this.gpsCoordinaat = gpsCoordinaat;
   }

   public Adres(final Adres adres) {
      super(adres);
      this.city = adres.getCity();
      this.country = adres.getCountry();
      this.gpsCoordinaat = adres.getGpsCoordinaat();
      this.houseNumber = adres.getHouseNumber();
      this.numberSuffix = adres.getNumberSuffix();
      this.postalCode = adres.getPostalCode();
      this.state = adres.getState();
      this.street = adres.getStreet();
   }

   @Override
   public int hashCode() {
      return this.gpsCoordinaat.hashCode();
   }

   @Override
   public int compareTo(final Adres that) {
      return this.getGpsCoordinaat().compareTo(that.getGpsCoordinaat());
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Adres [street=");
      builder.append(this.street);
      builder.append(", houseNumber=");
      builder.append(this.houseNumber);
      builder.append(", numberSuffix=");
      builder.append(this.numberSuffix);
      builder.append(", city=");
      builder.append(this.city);
      builder.append(", state=");
      builder.append(this.state);
      builder.append(", postalCode=");
      builder.append(this.postalCode);
      builder.append(", country=");
      builder.append(this.country);
      builder.append(", gpsCoordinaat=");
      builder.append(this.gpsCoordinaat);
      builder.append(super.toString());
      return builder.toString();
   }

   public String getStreet() {
      return this.street;
   }

   public void setStreet(final String street) {
      this.street = street;
   }

   public Integer getHouseNumber() {
      return this.houseNumber;
   }

   public void setHouseNumber(final Integer houseNumber) {
      this.houseNumber = houseNumber;
   }

   public String getNumberSuffix() {
      return this.numberSuffix;
   }

   public void setNumberSuffix(final String numberSuffix) {
      this.numberSuffix = numberSuffix;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(final String city) {
      this.city = city;
   }

   public String getState() {
      return this.state;
   }

   public void setState(final String state) {
      this.state = state;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(final String postalCode) {
      this.postalCode = postalCode;
   }

   public String getCountry() {
      return this.country;
   }

   public void setCountry(final String country) {
      this.country = country;
   }

   public EmbeddableGPSCoordinaat getGpsCoordinaat() {
      return this.gpsCoordinaat;
   }
}
