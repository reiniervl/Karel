package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = Boeking.DELETE_BY_OID, query = "delete from Boeking a where a.oid=:oid")
public class Boeking extends AbstractEntity<Boeking> {
	private static final long serialVersionUID = 1L;
	public static final String DELETE_BY_OID = "Boeking_DeleteByOid";
	@NotNull
	@JoinColumn(unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Reservering reservering;
	
	public Boeking() {
	}
	
	public Boeking(Reservering reservering) {
		if(reservering == null) {
			throw new IllegalArgumentException("Reservering mag niet null zijn");
		}

		this.reservering = reservering.clone();
	}
	
	public Boeking(Boeking boeking) {
		super(boeking);
		this.reservering = boeking.getReservering();
	}
	
	public Reservering getReservering() {
		return this.reservering;
	}

	@Override
	public int compareTo(Boeking that) {
		return this.getReservering().compareTo(that.getReservering());
	}

	@Override
	public int hashCode() {
		return this.getReservering().hashCode();
	}

	@Override
	public String toString() {
		return "Boeking [reservering=" + reservering + "]";
	}
}
