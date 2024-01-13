package com.example.customer.exception;

import com.example.customer.entityModel.ExceptionHandlerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
@Slf4j
/**
 * CustomerExecptionHandler - Exception will be thrown when CustomRunTimeException get caught with message
 * Error will be based on business logic validations
 * @author manjunathreddy1999
 */
public class CustomerExecptionHandler {

    @ExceptionHandler(value = CustomRunTimeException.class)
    public ResponseEntity<ExceptionHandlerModel> exceptionHandler(CustomRunTimeException exception) {
        log.info("Setting the CustomerRunTimeException response based on message");
        ExceptionHandlerModel exe = new ExceptionHandlerModel();
        exe.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exe.setTimeStamp(LocalDateTime.now());
        exe.setErrorMessage(exception.getMessage());
        exe.setPath(exception.getStackTrace()[0].getClassName());
        return new ResponseEntity<>(exe, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
