package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("r")
@NamedQueries({
@NamedQuery(name = RivierTocht.DELETE_BY_OID, query = "delete from RivierTocht a where a.oid=:oid"),
@NamedQuery(name = RivierTocht.FIND_BY_BOOT_OID, query = "SELECT t FROM RivierTocht t WHERE t.boot.oid=:boot_oid")
})
public class RivierTocht extends Tocht<RivierTocht> {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BOOT_OID = "RivierTocht_FindByBootOid";
	public static final String DELETE_BY_OID = "RIVIERTOCHT_DELETEBYOID";

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
}
