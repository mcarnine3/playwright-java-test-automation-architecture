package io.github.com.carnine.annotation;

import io.github.com.carnine.dto.BaseDto;
import io.github.com.carnine.util.DataArgumentsProvider;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

/**
 * @author mcarnine3
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(DataArgumentsProvider.class)
public @interface DataSource {

    String id();

    String fileName();

    Class<? extends BaseDto> clazz();
}
