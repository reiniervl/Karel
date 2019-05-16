package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("m")
@NamedQuery(name = MeerTocht.DELETE_BY_OID, query = "delete from MeerTocht a where a.oid=:oid")
public class MeerTocht extends Tocht<MeerTocht> {
	public static final String DELETE_BY_OID = "MeerTocht_DeleteByOid";
	private static final long serialVersionUID = 1L;

	public MeerTocht() {
		super();
	}

	public MeerTocht(Boot boot, double prijs, Periode reserveringsPeriode) {
		super(boot, prijs, reserveringsPeriode);
	}

	public MeerTocht(MeerTocht tocht) {
		super(tocht);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeerTocht []");
		builder.append(", reserveringsPeriode=");
		builder.append(super.getReserveringsPeriode());
		builder.append(", actuelePeriode=");
		builder.append(super.getActuelePeriode());
		builder.append(", prijs=");
		builder.append(super.getPrijs());
		return builder.toString();
	}
}
