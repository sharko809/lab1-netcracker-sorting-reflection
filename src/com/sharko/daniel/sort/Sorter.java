package com.sharko.daniel.sort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         An annotation used for sorting methods. Can be applied to methods
 *         only.
 *         </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sorter {
	int str();
}
