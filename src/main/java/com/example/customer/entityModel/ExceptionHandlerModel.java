package com.example.customer.entityModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
/**
 * The exceptionHandlerModel Class is using to handling CustomRuntimeException response
 * @author manjunathreddy1999
 */
public class ExceptionHandlerModel {

    private HttpStatus statusCode;
    private String errorMessage;
    private LocalDateTime timeStamp;
    private String path;
}
