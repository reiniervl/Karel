package se.skillytaire.belastingdienst.ee.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;

@Entity
public class Account extends AbstractEntity<Account> {
	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne
	private Klant klant;

	@OneToMany
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