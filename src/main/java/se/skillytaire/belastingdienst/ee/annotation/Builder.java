package se.skillytaire.belastingdienst.ee.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Alle instance variabelen worden gebruikt om een builder class te maken. De
 * class die wordt gegenereerd is de naam van de originele class + 'Builder'.
 * </p>
 *
 * @author Reinier van Leussen
 * @version 0.8.2
 */
@Target({ TYPE })
@Retention(RetentionPolicy.CLASS)
public @interface Builder {
}
