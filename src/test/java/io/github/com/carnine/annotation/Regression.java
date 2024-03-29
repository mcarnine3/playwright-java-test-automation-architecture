package io.github.com.carnine.annotation;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

/**
 * @author mcarnine3
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("regression")
public @interface Regression {}
