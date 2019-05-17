package se.skillytaire.belastingdienst.ee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.rvlstudio.annotation.Builder;
import com.rvlstudio.annotation.BuilderField;

import se.skillytaire.java.datatype.PositiveInteger;

@Entity
@Builder
@NamedQueries({ 
	@NamedQuery(name = Boot.DELETE_BY_OID, query = "delete from Boot a where a.oid=:oid"),
	@NamedQuery(name = Boot.SELECT_BY_QRCODE, query = "select a from Boot a where a.nummer=:bootNummer AND a.eigenaar.userName=:verhuurder")
})
@Table(
	uniqueConstraints = { 
		@UniqueConstraint(
			name = "Uniqueverhuurdericmnummer",
			columnNames = { "verhuurder_oid", "nummer" }
		)
})
public class Boot extends AbstractEntity<Boot> {
	private static final long serialVersionUID = 1L;
	public static final String DELETE_BY_OID = "Boot_DeleteByOid";
	public static final String SELECT_BY_QRCODE = "Boot_SelectByQrcode";
	@NotNull
	@Column (name = "nummer")
	@BuilderField
	private int nummer;

	// TODO: Correct cascadetype
	// UNDONE: CascadeType.PERSIST, CascadeType.MERGE
	@OneToMany(cascade = { CascadeType.ALL})
	private List<Tocht<?>> tochten = new ArrayList<>();
	@NotNull
	@BuilderField
	@OneToOne(cascade = { CascadeType.ALL})
	@JoinColumn (name = "verhuurder_oid")
	private Verhuurder eigenaar;
//   private ArrayList<Tocht<?>> tochtGeschiedenis;
//   private static final Duration INSPECTIEDUUR = Duration.ofSeconds(10);

	public Boot() {
	}

	public Boot(final Verhuurder eenEigenaar, final PositiveInteger nummer) {
		if (eenEigenaar == null) {
			throw new IllegalArgumentException("eenEigenaar mag niet 'null' zijn");
		}
		if (nummer == null) {
			throw new IllegalArgumentException("nummer mag niet 'null' zijn!");
		}
		this.eigenaar = eenEigenaar;
		this.nummer = nummer.intValue();
//      this.tochtGeschiedenis = new ArrayList<Tocht<?>>();
	}

	public Boot(final Boot boot) {
		super(boot);
		this.nummer = boot.getNummer();
		this.eigenaar = boot.getEigenaar();
//      this.tochtGeschiedenis =
//            (ArrayList<Tocht<?>>) that.tochtGeschiedenis.clone();
//      if (that.hasLaatsteTocht()) {
//         // FIX-ME: Clone()
//         this.deLaatsteTocht = that.deLaatsteTocht;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Boot [bootNummer=");
		builder.append(this.nummer);
		builder.append("]");
		builder.append("Verhuurder [eigenaar=");
		builder.append(this.eigenaar);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(final Boot that) {
		int compareTo = this.getNummer() - that.getNummer();
		if (compareTo == 0) {
			compareTo = this.getEigenaar().compareTo(that.getEigenaar());
		}
		return compareTo;
	}

	@Override
	public int hashCode() {
		return this.getNummer();
	}

	public void addTocht(Tocht<?> tocht) {
		this.tochten.add(tocht);
	}

	public void removeTocht(Tocht<?> tocht) {
		this.tochten.remove(tocht);
	}

	public int getNummer() {
		return this.nummer;
	}

	public boolean hasNummer(final int nummer) {
		return (this.nummer == nummer);
	}

	public Verhuurder getEigenaar() {
		return this.eigenaar;
	}

// public void start(final Tocht<?> eenTocht) {
//    this.deLaatsteTocht = eenTocht;
////    this.tochtGeschiedenis.add(this.deLaatsteTocht);
//    eenTocht.start();
// }
// public boolean isBeschikbaar() {
//    return ((this.isVrij()) && (!this.isInspectieNodig()));
// }
//
// public boolean isInspectieNodig() {
//    Duration total = Duration.ZERO;
//    for (Tocht<?> t : this.tochtGeschiedenis) {
//       Periode actuelePeriode = t.getActuelePeriode();
//       Optional<Duration> optionalDuration = actuelePeriode.getDuur();
//       if (optionalDuration.isPresent()) {
//          total = total.plus(optionalDuration.get());
//       }
//    }
//    return total.getSeconds() > Boot.INSPECTIEDUUR.getSeconds();
// }
//
// private boolean isVrij() {
//    return (!this.hasLaatsteTocht()) || (this.deLaatsteTocht.isBeeindigd());
// }
//
// public boolean hasLaatsteTocht() {
//    return this.deLaatsteTocht != null;
// }
//
// public void uitvoerenInspectie() {
//    this.tochtGeschiedenis.clear();
// }

}
