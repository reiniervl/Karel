package se.skillytaire.belastingdienst.ee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.rvlstudio.annotation.Builder;
import com.rvlstudio.annotation.BuilderField;

import java.util.ArrayList;

@Builder
@Entity
@NamedQueries({
	@NamedQuery(name = "FIND_BY_USERNAME", query = "SELECT a FROM Account a WHERE a.klant.username=:username")
})
public class Account extends AbstractEntity<Account> {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_USERNAME = "FIND_BY_USERNAME";

	@BuilderField
	@NotNull
	@JoinColumn(name = "Klant_OID", unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Klant klant;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Reservering> reserveringen = new ArrayList<Reservering>();
	
	/**
	 * Constructor alleen te gebruiken door JPA
	 */
	public Account() {	}

	@SuppressWarnings("unchecked")
	public Account(Account account) {
		if(account == null) {
			throw new IllegalArgumentException("Account mag niet null zijn");
		}

		this.klant = account.getKlant().clone();
		this.reserveringen = (ArrayList<Reservering>) ((ArrayList<Reservering>)account.reserveringen).clone();
	}

	public Account(final Klant klant) {
		if(klant == null) {
			throw new IllegalArgumentException("Klant mag niet null zijn");
		}
		this.klant = klant;
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
}