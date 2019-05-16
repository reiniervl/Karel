package se.skillytaire.belastingdienst.ee.common;

public class QRCode extends AbstractComparableObject<QRCode> {
	private static final long serialVersionUID = 1L;

	private final String username;
	private final int bootNummer;

	public QRCode(String username, int nummer) {
		if(username == null) {
			throw new IllegalArgumentException("username mag niet null zijn");
		}
		this.username = username;
		this.bootNummer = nummer;
	}
	

	@Override
	public int compareTo(QRCode code) {
		return this.username.compareTo(code.getUsername());
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	public String getUsername() {
		return username;
	}

	public int getBootNummer() {
		return bootNummer;
	}

	@Override
	public String toString() {
		return "QRCode [bootNummer=" + bootNummer + ", username=" + username + "]";
	}
}