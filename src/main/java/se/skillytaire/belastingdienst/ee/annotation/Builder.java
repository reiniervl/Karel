package se.skillytaire.belastingdienst.ee.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Fields in the annotated class will need to be used for the Builder
 * Pattern</p>
 *
 * @author Reinier van Leussen
 * @version 0.0.1
 */
@Target({TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Builder {
}
