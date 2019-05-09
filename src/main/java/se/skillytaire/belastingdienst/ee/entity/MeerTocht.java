package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("m")
@NamedQuery(name = MeerTocht.DELETE_BY_OID, query = "delete from MeerTocht a where a.oid=:oid")
public class MeerTocht extends Tocht<MeerTocht> {
	public static final String DELETE_BY_OID = "MeerTocht_DeleteByOid";

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	public MeerTocht() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeerTocht(double prijs, Periode reserveringsPeriode) {
		super(prijs, reserveringsPeriode);
		// TODO Auto-generated constructor stub
	}

	public MeerTocht(MeerTocht tocht) {
		super(tocht);
		// TODO Auto-generated constructor stub
	}

	public static Object getBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(MeerTocht thisMeerTocht) {
		// TODO Auto-generated method stub

	}

}
