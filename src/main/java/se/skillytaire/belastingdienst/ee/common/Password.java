package se.skillytaire.belastingdienst.ee.common;

public class Password extends AbstractComparableObject<Password> {
	private static final long serialVersionUID = 1L;
	// {
	// 	this.addConstraint((v) -> v != null);
	// 	this.addConstraint((v) -> v.length() >= 4);
	// 	this.addConstraint((v) -> v.length() <= 24);
	// }
	private final String value;

	public Password(final String value) {
		if(value == null) {
			 throw new IllegalArgumentException("value is void");
		}
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
		return this.getValue();
 }

	@Override
	public int compareTo(Password arg) {
		return this.value.compareTo(arg.getValue());
	}
}