package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

//TODO klant en tocht verwerken
@Entity
@Table(uniqueConstraints = {
      @UniqueConstraint(name = "UniqueReservering", columnNames = {
            "ReserveringsNummer", "reserveringsDatum", "verloopDatum" }) })
@NamedQueries({ @NamedQuery(name = Reservering.FIND_BY_RESNUMMER,
      query = "select a from Reservering a where a.reserveringsNummer=:reserveringsNummer"),
      @NamedQuery(name = Reservering.DELETE_BY_OID,
            query = "delete from Reservering a where a.oid=:oid") })
public class Reservering extends AbstractEntity<Reservering> {
   private static final long serialVersionUID = 1L;
   public static final String FIND_BY_RESNUMMER = "Reservering_FindByResnummer";
   public static final String DELETE_BY_OID = "Reservering_DeleteByOid";
   @NotNull
   @Basic
   @Column(unique = true)
   private Integer reserveringsNummer;
   @NotNull
   @Basic
   private LocalDateTime reserveringsDatum;
   @NotNull
   @Basic
   private LocalDateTime verloopDatum;
   @OneToMany(cascade = CascadeType.ALL, targetEntity = MeerTocht.class)
   private Collection<Tocht<MeerTocht>> mijnMeerTochten =
         new ArrayList<Tocht<MeerTocht>>();
   @ManyToOne(cascade = CascadeType.ALL)
   private Klant mijnKlant;

   /**
    * Developers should not use the default constructor. Please use the same
    * visibility modifier "protected" for overriding classes.
    */
   public Reservering() {
   }

   public Reservering(final Integer reserveringsNummer) {
      if (reserveringsNummer == null) {
         throw new IllegalArgumentException("Het reserveringsNummer is Null");
      }
      this.reserveringsNummer = reserveringsNummer;
   }

   public Reservering(final Reservering reservering) {
      super(reservering);
      this.reserveringsNummer = reservering.getReserveringsNummer();
      this.reserveringsDatum = reservering.getReserveringsDatum();
      this.verloopDatum = reservering.getVerloopDatum();
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Reservering [Reserveringsnummer= ");
      builder.append(this.reserveringsNummer);
      builder.append("Reserverings datum= ");
      builder.append(this.reserveringsDatum);
      builder.append("Verloop datum= ");
      builder.append(this.verloopDatum);
      builder.append("]");
      return builder.toString();
   }

   @Override
   public int compareTo(final Reservering that) {
      return this.getReserveringsNummer()
            .compareTo(that.getReserveringsNummer());
   }

   @Override
   public int hashCode() {
      return this.reserveringsNummer.hashCode();
   }

   public Integer getReserveringsNummer() {
      return this.reserveringsNummer;
   }

   public LocalDateTime getReserveringsDatum() {
      return this.reserveringsDatum;
   }

   public void setReserveringsDatum(final LocalDateTime reserveringsDatum) {
      this.reserveringsDatum = reserveringsDatum;
   }

   public LocalDateTime getVerloopDatum() {
      return this.verloopDatum;
   }

   public void setVerloopDatum(final LocalDateTime verloopDatum) {
      this.verloopDatum = verloopDatum;
   }
}
