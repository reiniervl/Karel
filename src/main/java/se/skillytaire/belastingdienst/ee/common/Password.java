package se.skillytaire.belastingdienst.ee.common;

public class Password extends AbstractComparableObject<String> {
	private static final long serialVersionUID = 1L;
	// {
	// 	this.addConstraint((v) -> v != null);
	// 	this.addConstraint((v) -> v.length() >= 4);
	// 	this.addConstraint((v) -> v.length() <= 24);
	// }
	private final String value;

	public boolean verify(Password password) {
		return password != null && this.getValue().equals(password.getValue());
	}

	public Password(final String value) {
		this.value = value;
 }

 public String getValue() {
	 return this.value;
 }

 @Override
 public int hashCode() {
		return getValue().hashCode();
 }

 @Override
 public String toString() {
		return getValue();
 }

	@Override
	public int compareTo(String arg) {
		return this.value.compareTo(arg);
	}
}