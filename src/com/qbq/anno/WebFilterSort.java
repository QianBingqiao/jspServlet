package com.qbq.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.servlet.DispatcherType;

import java.lang.annotation.RetentionPolicy;

@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebFilterSort {
	  public abstract int value();

	  public abstract boolean isMatchAfter();

	  public abstract String displayName();

	  public abstract String filterName();

	  public abstract String[] servletNames();

	  public abstract String[] urlPatterns();

	  public abstract DispatcherType[] dispatcherTypes();

	  public abstract boolean asyncSupported();

}
