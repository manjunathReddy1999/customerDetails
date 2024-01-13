package com.example.customer.service;

import com.example.customer.entityModel.CustomerDetails;
import com.example.customer.exception.CustomRunTimeException;
import com.example.customer.requestModel.CustomerRequest;

/**
 * customerService Interface contains method declaration of CRUD operation method for Customer
 * @author manjunathreddy1999
 */
public interface CustomerService {

    CustomerDetails saveCustomer(CustomerRequest request) throws CustomRunTimeException;
}
