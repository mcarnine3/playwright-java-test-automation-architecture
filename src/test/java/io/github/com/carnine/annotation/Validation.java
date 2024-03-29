package io.github.com.carnine.annotation;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

/**
 * @author mcarnine3
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Regression
@Tag("validation")
public @interface Validation {}
