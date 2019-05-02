package se.skillytaire.belastingdienst.ee.common;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTransferableObject<T> implements Validatable<T> {
   private Set<Constraint<T>> constraints = new HashSet<>();
   private T value;

   @Override
   public boolean validate() {
      boolean validated = false;
      for (Constraint<T> c : this.constraints) {
         if (!(validated = c.criteriumMet(this.value))) {
            break;
         }
      }
      return validated;
   }

   @Override
   public boolean validate(final T t) {
      boolean validated = false;
      for (Constraint<T> c : this.constraints) {
         if (!(validated = c.criteriumMet(t))) {
            break;
         }
      }
      return validated;
   }

   @Override
   public void addConstraint(final Constraint<T> constraint) {
      this.constraints.add(constraint);
   }

   @Override
   public void removeConstraint(final Constraint<T> constraint) {
      this.constraints.remove(constraint);
   }

   public T getValue() {
      return this.value;
   }

   public void setValue(final T value) {
      this.value = value;
   }
}
