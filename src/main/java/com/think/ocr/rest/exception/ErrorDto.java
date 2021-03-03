/*
 * Copyright Stuff Limited
 */

package com.think.ocr.rest.exception;

import lombok.Data;
import java.time.Instant;

@Data
public class ErrorDto {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
