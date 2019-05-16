package se.skillytaire.belastingdienst.ee.common;

public class QRCode extends AbstractComparableObject<QRCode> {
	private static final long serialVersionUID = 1L;

	private final String username;
	private final int nummer;

	public QRCode(String username, int nummer) {
		this.username = username;
		this.nummer = nummer;
	}
	

	@Override
	public int compareTo(QRCode arg0) {
		return 0;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getUsername() {
		return username;
	}

	public int getNummer() {
		return nummer;
	}
	
}