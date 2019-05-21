package se.skillytaire.belastingdienst.ee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.rvlstudio.annotation.Builder;
import com.rvlstudio.annotation.BuilderField;

import java.util.ArrayList;

@Builder
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueAccount", columnNames = { "Klant_OID",
"verhuurder"}) })
@NamedQueries({
	@NamedQuery(name = "FIND_BY_UC", query = "SELECT a FROM Account a WHERE a.klant.username=:usernameklant AND a.verhuurder.userName=:usernameverhuurder")
})
public class Account extends AbstractEntity<Account> {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_UC = "FIND_BY_UC";

	@BuilderField
	@NotNull
	@JoinColumn(name = "Klant_OID", unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Klant klant;

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	private List<Reservering> reserveringen = new ArrayList<Reservering>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn (name = "verhuurder")
	private Verhuurder verhuurder;

	/**
	 * Constructor alleen te gebruiken door JPA
	 */
	public Account() {	}

	@SuppressWarnings("unchecked")
	public Account(Account account) {
		if(account == null) {
			throw new IllegalArgumentException("Account mag niet null zijn");
		}
		this.verhuurder = account.getVerhuurder().clone();
		this.klant = account.getKlant().clone();
		this.reserveringen = (ArrayList<Reservering>) ((ArrayList<Reservering>)account.reserveringen).clone();
	}

	public Account(final Klant klant, final Verhuurder verhuurder) {
		if(klant == null) {
			throw new IllegalArgumentException("Klant mag niet null zijn");
		}
		if(verhuurder == null) {
			throw new IllegalArgumentException("verhuurder mag niet null zijn");
		}
		this.klant = klant;
		this.verhuurder = verhuurder;
	}

	public boolean add(Reservering reservering) {
		return this.reserveringen.add(reservering);
	}

	public boolean remove(Reservering reservering) {
		return this.reserveringen.remove(reservering);
	}

	@Override
	public int compareTo(Account account) {
		return this.getKlant().compareTo(account.getKlant());
	}

	@Override
	public int hashCode() {
		return this.getKlant().hashCode();
	}

	/**
	 * @return the klant
	 */
	public Klant getKlant() {
		return klant;
	}
	
	public Verhuurder getVerhuurder() {
		return verhuurder;
	}
}