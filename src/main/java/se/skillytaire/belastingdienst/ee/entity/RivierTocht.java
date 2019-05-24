package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("r")
@NamedQueries({
@NamedQuery(name = RivierTocht.DELETE_BY_OID, query = "delete from RivierTocht a where a.oid=:oid"),
@NamedQuery(name = RivierTocht.BESCHIKBARE_TOCHTEN, query = "SELECT rt FROM RivierTocht rt, Reservering r WHERE rt.oid != (SELECT mt.oid FROM r.mijnTochten mt)")
})
public class RivierTocht extends Tocht<RivierTocht> {
	private static final long serialVersionUID = 1L;
	public static final String DELETE_BY_OID = "RIVIERTOCHT_DELETEBYOID";
	public static final String BESCHIKBARE_TOCHTEN = "RIVIERTOCHT_BESCHIKBARE_BOTEN";

	public RivierTocht() {
	}

	public RivierTocht(double prijs, Periode reserveringsPeriode) {
		super(prijs, reserveringsPeriode);
	}

	public RivierTocht(RivierTocht tocht) {
		super(tocht);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RivierTocht []");
		builder.append(", reserveringsPeriode=");
		builder.append(super.getReserveringsPeriode());
		builder.append(", actuelePeriode=");
		builder.append(super.getActuelePeriode());
		builder.append(", prijs=");
		builder.append(super.getPrijs());
		return builder.toString();
	}
}
