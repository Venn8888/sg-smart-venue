package com.sg.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface SequenceColumn {
	String value() default "";
}
