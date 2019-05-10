package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("r")
@NamedQuery(name = RivierTocht.DELETE_BY_OID, query = "delete from RivierTocht a where a.oid=:oid")
public class RivierTocht extends Tocht<RivierTocht> {
	private static final long serialVersionUID = 1L;
	public static final String DELETE_BY_OID = "RivierTocht_DeleteByOid";

	public RivierTocht() {
	}

	public RivierTocht(Boot boot, double prijs, Periode reserveringsPeriode) {
		super(boot, prijs, reserveringsPeriode);
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

	@Override
	public int compareTo(final RivierTocht that) {
		return this.getReserveringsPeriode().compareTo(that.getReserveringsPeriode());
	}
}
