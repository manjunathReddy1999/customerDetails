package com.example.customer.serviceImpl;

import com.example.customer.entityModel.CustomerDetails;
import com.example.customer.exception.CustomRunTimeException;
import com.example.customer.repository.CustomerDetailsRepository;
import com.example.customer.requestModel.CustomerRequest;
import com.example.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


/**
 * This customerServiceImpl class is used to Insert customer record from given data with business logic validations
 * @author manjunathreddy1999
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    /**
     * saveCustomer method save Customer details to CUSTOMER_DETAILS table.
     * @param request
     * @return
     * @throws CustomRunTimeException
     */
    @Override
    public CustomerDetails saveCustomer(CustomerRequest request) throws CustomRunTimeException {

        log.debug("customerServiceImpl.saveCustomer() method entered");
        log.debug("Checking whether the Customer Age is 18 + or not, Customer Age is {}", request.getDob());
        if (this.isAdultCustomer(request.getDob())) {
            log.debug("Checking whether the Customer Email ID already exists or not, Customer Email is {}", request.getEmail());
            if (!customerDetailsRepository.getRecordByEmailId(request.getEmail()).isEmpty()) {
                log.debug("Customer Email ID already exists in our System, Customer Email is {}", request.getEmail());
                throw new CustomRunTimeException("Already " + request.getEmail() + " exists in our system. Please try with another Email-Id");
            }

            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setName(request.getName());
            customerDetails.setEmail(request.getEmail());
            customerDetails.setDob(request.getDob());
            customerDetails.setOccupation(request.getOccupation());
            log.info("Assign Customer Group based on Email Id and Occupation of Customer");
            if (request.getEmail().contains("@hikeOn.tech")) {
                customerDetails.setCustomerGroup("hikeon");
            } else if (request.getOccupation().toString().equalsIgnoreCase("developer")) {
                customerDetails.setCustomerGroup("developer");
            } else if (request.getOccupation().toString().equalsIgnoreCase("chef")) {
                customerDetails.setCustomerGroup("chef");
            } else {
                customerDetails.setCustomerGroup("NA");
            }
            customerDetails.setCreatedDate(LocalDate.now());
            customerDetails.setCreatedBy("SYSTEM");
            try {
                log.info("Inserting Customer Details into the Data Table");
                customerDetailsRepository.save(customerDetails);
            } catch (Exception exe) {
                log.debug("Customer Details already exists in our System. Duplicate Record Found {} {} {}", request.getOccupation(),
                        request.getDob(), customerDetails.getCustomerGroup());
                throw new CustomRunTimeException("Duplicate Record Found. Please enter different details/Edit existing customer details.");
            }
            log.debug("customerServiceImpl.saveCustomer() method exited");
            return customerDetails;
        } else {
            log.debug("Customer Age is below 18, Customer Age given {}", request.getDob());
            throw new CustomRunTimeException("Age should be above 18+.");
        }
    }

    private boolean isAdultCustomer(String dob) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dob, formatter);
        Period period = Period.between(date, currentDate);
        int age = period.getYears();
        return age >= 18;
    }
}
