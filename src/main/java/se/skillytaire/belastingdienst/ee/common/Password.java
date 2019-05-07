package se.skillytaire.belastingdienst.ee.common;

public class Password extends AbstractTransferableObject<String> {
	{
		this.addConstraint((v) -> v != null);
		this.addConstraint((v) -> v.length() >= 4);
		this.addConstraint((v) -> v.length() <= 24);
	}

	public boolean verify(Password password) {
		return password != null && this.getValue().equals(password.getValue());
	}

	public Password(final String value) {
		this.setValue(value);
 }
}