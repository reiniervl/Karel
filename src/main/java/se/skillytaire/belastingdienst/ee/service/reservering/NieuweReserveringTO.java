package se.skillytaire.belastingdienst.ee.service.reservering;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.entity.Tocht;

public class NieuweReserveringTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotNull
	private final LocalDateTime reserveringsDatum;
	@NotNull
	private final Periode verloopDatum = new Periode();
	@NotNull
	@Size(min = 1)
	private final List<Tocht<?>> mijnTochten = new ArrayList<Tocht<?>>();
	@NotNull
	private final Account account;

	public NieuweReserveringTO(Account account, MeerTocht meerTocht) {
		if (account == null) {
			 throw new IllegalArgumentException("account mag niet null zijn");
		}
		if (meerTocht == null) {
			 throw new IllegalArgumentException("meerTocht mag niet null zijn");
		}
		this.add(meerTocht);
		this.reserveringsDatum = LocalDateTime.now();
		this.verloopDatum.start();
		this.account = account;
 }
	
	public NieuweReserveringTO(Account account, RivierTocht rivierTocht) {
		if (account == null) {
			 throw new IllegalArgumentException("account mag niet null zijn");
		}
		if (rivierTocht == null) {
			 throw new IllegalArgumentException("rivierTocht mag niet null zijn");
		}
		this.add(rivierTocht);
		this.reserveringsDatum = LocalDateTime.now();
		this.verloopDatum.start();
		this.account = account;
 }
	public boolean add(final Tocht<?> e) {
		return this.mijnTochten.add(e);
	}
	public LocalDateTime getReserveringsDatum() {
		return reserveringsDatum;
	}
	public Periode getVerloopDatum() {
		return verloopDatum;
	}
	public List<Tocht<?>> getMijnTochten() {
		return mijnTochten;
	}
	public Account getAccount() {
		return account;
	}
	@Override
	public String toString() {
		return "NieuweReserveringTO [reserveringsDatum=" + reserveringsDatum + ", verloopDatum=" + verloopDatum
				+ ", mijnTochten=" + mijnTochten + ", account=" + account + "]";
	}
	
}
