package se.skillytaire.belastingdienst.ee.common;

@FunctionalInterface
public interface Constraint<T> {
   boolean criteriumMet(T t);
}
