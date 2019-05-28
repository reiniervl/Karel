package se.skillytaire.belastingdienst.ee.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rvlstudio.annotation.Builder;
import com.rvlstudio.annotation.BuilderField;

@Builder
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueReservering", columnNames = { "accountOID",
		"reserveringsDatum"}) })
@NamedQuery(name = Reservering.DELETE_BY_OID, query = "delete from Reservering a where a.oid=:oid")
public class Reservering extends AbstractEntity<Reservering> {
	private static final long serialVersionUID = 1L;
	public static final String DELETE_BY_OID = "Reservering_DeleteByOid";
	@NotNull
	@Basic 
	@Column (name = "reserveringsDatum")
	private LocalDateTime reserveringsDatum;
	@NotNull
	@BuilderField
	@Embedded
	private Periode verloopDatum = new Periode();
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@NotNull
	@Size(min = 1)
	private List<Tocht<?>> mijnTochten = new ArrayList<Tocht<?>>();
	@NotNull
	@BuilderField
	@JoinColumn (name = "accountOID")
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Account account;

	/**
	 * Developers should not use the default constructor. Please use the same
	 * visibility modifier "protected" for overriding classes.
	 */
	public Reservering() {
	}
	public Reservering(final Account account, final MeerTocht tocht) {
		if (account == null) {
			throw new IllegalArgumentException("Account is Null");
		}
		if (tocht == null) {
			throw new IllegalArgumentException("tocht is Null");
		}
		this.add(tocht);
		this.reserveringsDatum = LocalDateTime.now();
		this.verloopDatum.start();
		this.account = account;
	}
	public Reservering(final Account account, final RivierTocht tocht) {
		if (account == null) {
			throw new IllegalArgumentException("Account is Null");
		}
		if (tocht == null) {
			throw new IllegalArgumentException("tocht is Null");
		}
		this.add(tocht);
		this.reserveringsDatum = LocalDateTime.now();
		this.verloopDatum.start();
		this.account = account;
	}

	

	@SuppressWarnings("unchecked")
	public Reservering(final Reservering reservering) {
		super(reservering);
		this.mijnTochten = (ArrayList<Tocht<?>>) ((ArrayList<Tocht<?>>)reservering.mijnTochten).clone();
		this.reserveringsDatum = reservering.getReserveringsDatum();
		this.verloopDatum = reservering.getVerloopDatum();
		this.account = reservering.getAccount();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account = ").append(this.account).append("Reservering Reserveringsnummer= ")
				.append(this.getOid()).append("Reserverings datum= ").append(this.reserveringsDatum)
				.append("Verloop datum= ").append(this.verloopDatum).append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(final Reservering that) {
		int compareTo = this.getAccount().compareTo(that.getAccount());
		if (compareTo == 0) {
			compareTo = this.getReserveringsDatum().compareTo(that.getReserveringsDatum());
		}
		return compareTo;
	}

	@Override
	public int hashCode() {
		return this.account.hashCode();
	}

	public Account getAccount() {
		return this.account;
	}

	@Transient
	public Optional<Integer> getReserveringsNummer() {
		return Optional.ofNullable(this.getOid());
	}

	public LocalDateTime getReserveringsDatum() {
		return this.reserveringsDatum;
	}

	public void setReserveringsDatum(final LocalDateTime reserveringsDatum) {
		this.reserveringsDatum = reserveringsDatum;
	}

	public Periode getVerloopDatum() {
		return this.verloopDatum;
	}

	public boolean add(final Tocht<?> e) {
		return this.mijnTochten.add(e);
	}

	public boolean remove(final Tocht<?> e) {
		return this.mijnTochten.remove(e);
	}
}
