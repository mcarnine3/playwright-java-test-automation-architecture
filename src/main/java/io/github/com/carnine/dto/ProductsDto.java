package io.github.com.carnine.dto;

import com.univocity.parsers.annotations.Parsed;

import lombok.Getter;
import lombok.ToString;

/**
 * @author mcarnine3
 */
@Getter
@ToString(callSuper = true)
public final class ProductsDto extends BaseDto {

    @Parsed(field = "Username", defaultNullRead = "")
    private String username;

    @Parsed(field = "Password", defaultNullRead = "")
    private String password;

    @Parsed(field = "URL", defaultNullRead = "")
    private String url;
}