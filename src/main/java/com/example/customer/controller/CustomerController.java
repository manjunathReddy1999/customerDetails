package com.example.customer.controller;

import com.example.customer.entityModel.CustomerDetails;
import com.example.customer.exception.CustomRunTimeException;
import com.example.customer.requestModel.CustomerRequest;
import com.example.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Slf4j
/**
 * CustomerConroller class contains a Save Customer API
 * @author github - manjunathreddy1999
 * created date - 13/01/2024
 */
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity<CustomerDetails> saveCustomerDetails(@RequestBody CustomerRequest request) throws CustomRunTimeException {
            log.info("customerController.saveCustomerDetails() entered");
            CustomerDetails savedCustomer = customerService.saveCustomer(request);
            log.info("customerController.saveCustomerDetails() exited");
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


}
