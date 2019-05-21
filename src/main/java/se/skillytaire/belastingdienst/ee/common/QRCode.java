package se.skillytaire.belastingdienst.ee.common;

public class QRCode extends AbstractComparableObject<QRCode> {
	private static final long serialVersionUID = 1L;

	private final Integer boekingsNummer;
	
	public QRCode(final Integer boekingsNummer) {
		if (boekingsNummer == null) {
			throw new IllegalArgumentException("boekingsnummer is null");
		}
		this.boekingsNummer = boekingsNummer;
	}
	
	public Integer getBoekingsNummer() { 
		return this.boekingsNummer;
	}
	
	@Override
	public int compareTo(QRCode that) {
		return this.boekingsNummer.compareTo(that.boekingsNummer);
	}

	@Override
	public int hashCode() {
		return this.boekingsNummer.hashCode();
	}

	@Override
	public String toString() {
		return "QRCode [boekingsNummer=" + boekingsNummer + "]";
	}
	
}