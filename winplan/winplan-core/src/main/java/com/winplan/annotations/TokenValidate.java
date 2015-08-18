/**
 * TokenValidate.java.java
 * @author FengMy
 * @since 2015年7月24日
 */
package com.winplan.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
 * 功能描述：标识需要Token校验
 * 
 * @author FengMy
 * @since 2015年7月24日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValidate {

}
