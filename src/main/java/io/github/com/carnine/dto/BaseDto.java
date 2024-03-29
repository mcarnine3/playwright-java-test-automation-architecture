package io.github.com.carnine.dto;

import com.univocity.parsers.annotations.Parsed;

import lombok.Getter;
import lombok.ToString;

/**
 * @author mcarnine3
 */
@Getter
@ToString
public class BaseDto {

    @Parsed(field = "Test Case ID", defaultNullRead = "")
    private String testCaseId;
}
