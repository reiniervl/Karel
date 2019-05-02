package se.skillytaire.belastingdienst.ee.common;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTransferableObject<T> implements Validatable<T> {
   private Set<Constraint<T>> constraints = new HashSet<>();
   private T value;

   @Override
   public boolean validate() {
      boolean validated = false;
      for(Constraint<T> c : constraints) {
         if(!(validated = c.criteriumMet(this.value))) break;
      }
      return validated;
   }


   @Override
   public boolean validate(T t) {
      boolean validated = false;
      for(Constraint<T> c : constraints) {
         if(!(validated = c.criteriumMet(t))) break;
      }
      return validated;
   }

   @Override
   public void addConstraint(Constraint<T> constraint) {
      this.constraints.add(constraint);
   }

   @Override
   public void removeConstraint(Constraint<T> constraint) {
      this.constraints.remove(constraint);
   }

   public T getValue() {
      return value;
   }

   public void setValue(T value) {
      this.value = value;
   }
}
