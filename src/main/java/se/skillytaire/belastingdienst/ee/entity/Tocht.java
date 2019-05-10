package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
@DiscriminatorColumn(name = "type")
public abstract class Tocht<T extends Tocht<T>> extends AbstractEntity<T> {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = Periode.PROPERTY_START, column = @Column(name = "reserveringstart")),
			@AttributeOverride(name = Periode.PROPERTY_EIND, column = @Column(name = "reserveringeind")),
			@AttributeOverride(name = Periode.PROPERTY_DUUR, column = @Column(name = "reserveringduur")) })
	private Periode reserveringsPeriode;
	@NotNull
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = Periode.PROPERTY_START, column = @Column(name = "actuelestart")),
			@AttributeOverride(name = Periode.PROPERTY_EIND, column = @Column(name = "actueleeind")),
			@AttributeOverride(name = Periode.PROPERTY_DUUR, column = @Column(name = "actueleduur")) })
	private Periode actuelePeriode;
	@NotNull
	private double prijs;

	@OneToOne
	@NotNull
	private Boot boot;

	/**
	 * Developers should not use the default constructor. Please use the same
	 * visibility modifier "protected" for overriding classes.
	 */
	public Tocht() {
	}

	public Tocht(final Boot boot, final double prijs, final Periode reserveringsPeriode) {
		if (boot == null) {
			throw new IllegalArgumentException("De boot is null");
		}

		if (reserveringsPeriode == null) {
			throw new IllegalArgumentException("De reserveringsPeriode is null");
		}
		if (!reserveringsPeriode.isBeeindigd()) {
			throw new IllegalArgumentException("De reserveringsPeriode is niet valide");
		}
		this.boot = boot;
		this.prijs = prijs;
		this.reserveringsPeriode = reserveringsPeriode.clone();
		this.actuelePeriode = new Periode();
	}

	public Tocht(final T tocht) {
		super(tocht);
		this.boot = tocht.getBoot();
		this.reserveringsPeriode = tocht.getReserveringsPeriode();
		this.actuelePeriode = tocht.getActuelePeriode();
		this.prijs = tocht.getPrijs();
	}

	@Override
	public int compareTo(final T that) {
		Tocht<T> deTocht = that;
		return this.reserveringsPeriode.compareTo(deTocht.reserveringsPeriode) + this.boot.compareTo(that.getBoot());
	}

	@Override
	public int hashCode() {
		return this.reserveringsPeriode.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(", reserveringsPeriode=")
			.append(", actuelePeriode=")
			.append(this.actuelePeriode)
			.append(this.reserveringsPeriode)
			.append(", prijs=")
			.append(this.prijs)
			.append(", boot=")
			.append(this.boot);
		return builder.toString();
	}

	public Periode getReserveringsPeriode() {
		return this.reserveringsPeriode != null ? this.reserveringsPeriode.clone() : null;

	}

	public Periode getActuelePeriode() {
		return this.actuelePeriode != null ? this.actuelePeriode.clone() : null;
	}

	public double getPrijs() {
		return this.prijs;
	}

	public void setPrijs(final double prijs) {
		this.prijs = prijs;
	}

	public boolean isBeeindigd() {
		return this.actuelePeriode.isBeeindigd();
	}

	public void start() {
		this.actuelePeriode.start();
	}

	public Boot getBoot() {
		return this.boot;
	}
}
