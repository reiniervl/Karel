package se.skillytaire.belastingdienst.ee.common;
@Deprecated
public interface Validatable<T> {
   boolean validate();

   boolean validate(T t);

   void addConstraint(Constraint<T> constraint);

   void removeConstraint(Constraint<T> constraint);
}
