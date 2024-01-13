package com.example.customer.serviceImplTest;

import com.example.customer.entityModel.CustomerDetails;
import com.example.customer.enumeration.CustomerOccupation;
import com.example.customer.exception.CustomRunTimeException;
import com.example.customer.repository.CustomerDetailsRepository;
import com.example.customer.requestModel.CustomerRequest;
import com.example.customer.serviceImpl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Service
@Slf4j
public class CustomerServicemplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerDetailsRepository customerDetailsRepository;

    @Before(value = "")
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveCustomerSuccessTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/1999");
        request.setName("userName");
        request.setEmail("user.@hikeOn.tech");
        request.setOccupation(CustomerOccupation.valueOf("developer"));
        CustomerDetails actual = customerServiceImpl.saveCustomer(request);
        CustomerDetails expected = new CustomerDetails();
        expected.setCustomerGroup("hikeon");
        assertEquals(actual.getCustomerGroup(), actual.getCustomerGroup());
    }

    @Test
    public void saveCustomerSuccessDeveloperTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/1999");
        request.setName("userName");
        request.setEmail("user.@gmail.com");
        request.setOccupation(CustomerOccupation.valueOf("developer"));
        CustomerDetails actual = customerServiceImpl.saveCustomer(request);
        CustomerDetails expected = new CustomerDetails();
        expected.setCustomerGroup("developer");
        assertEquals(actual.getCustomerGroup(), actual.getCustomerGroup());
    }

    @Test
    public void saveCustomerSuccessChefTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/1999");
        request.setName("userName");
        request.setEmail("user.@gmail.com");
        request.setOccupation(CustomerOccupation.valueOf("chef"));
        CustomerDetails actual = customerServiceImpl.saveCustomer(request);
        CustomerDetails expected = new CustomerDetails();
        expected.setCustomerGroup("chef");
        assertEquals(actual.getCustomerGroup(), actual.getCustomerGroup());
    }

    @Test
    public void saveCustomerSuccessOtherTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/1999");
        request.setName("userName");
        request.setEmail("user.@gmail.com");
        request.setOccupation(CustomerOccupation.valueOf("other"));
        CustomerDetails actual = customerServiceImpl.saveCustomer(request);
        CustomerDetails expected = new CustomerDetails();
        expected.setCustomerGroup("NA");
        assertEquals(actual.getCustomerGroup(), actual.getCustomerGroup());
    }

    @Test
    public void saveCustomerBelowAgeTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/2022");
        request.setName("userName");
        request.setEmail("user.@gmail.com");
        request.setOccupation(CustomerOccupation.valueOf("other"));
        try {
            customerServiceImpl.saveCustomer(request);
        } catch(Exception e) {
            log.info("Error Occurred");
        }
    }

    @Test
    public void saveCustomerEmailExistTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/2002");
        request.setName("userName");
        request.setEmail("user.@gmail.com");
        request.setOccupation(CustomerOccupation.valueOf("other"));
        List<String> list = new ArrayList<>();
        String email1 = "user.@gmail.com";
        list.add(email1);

        when(customerDetailsRepository.getRecordByEmailId(Mockito.any())).thenReturn(list);
        try {
            customerServiceImpl.saveCustomer(request);
        } catch(Exception e) {
            log.info("Error Occurred");
        }
    }

    @Test
    public void saveCustomerUniqueExcepionTest() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setDob("11/11/2002");
        request.setName("userName");
        request.setEmail("user.@gmail.com");
        request.setOccupation(CustomerOccupation.valueOf("other"));

        when(customerDetailsRepository.save(Mockito.any())).
                thenThrow(new CustomRunTimeException("Duplicate Record Found. Please enter different details/Edit existing customer details."));
        try {
            customerServiceImpl.saveCustomer(request);
        } catch(Exception e) {
            log.info("Error Occurred");
        }
    }
}
