package com.sharko.daniel.array;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         An annotation used for array filling types. Can be applied to fields
 *         only.
 *         </p>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayFillingType {

}
